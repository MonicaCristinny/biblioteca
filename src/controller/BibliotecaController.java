package controller;

import database.LivroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory; // NECESSÁRIO
import model.Livros;
import view.BibliotecaView;
import java.util.List;

public class BibliotecaController {

    private BibliotecaView view;
    private LivroDAO livroDAO;
    private ObservableList<Livros> livrosObservableList;

    public BibliotecaController(BibliotecaView view) {
        this.view = view;
        this.livroDAO = new LivroDAO();
        this.livrosObservableList = FXCollections.observableArrayList();

        // Configuração das colunas (Mapeamento)
        this.view.getColId().setCellValueFactory(new PropertyValueFactory<>("id_livro"));
        this.view.getColTitulo().setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.view.getColCategoria().setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        this.view.getColAutor().setCellValueFactory(new PropertyValueFactory<>("id_autor"));
        this.view.getColStatus().setCellValueFactory(new PropertyValueFactory<>("status"));

        this.view.getTabelaLivros().setItems(livrosObservableList);
        this.atualizarTabela();

        this.view.getBtnPesquisar().setOnAction(event -> handlePesquisar());
        this.view.getBtnEmprestar().setOnAction(event -> handleEmprestar());
        this.view.getBtnDevolver().setOnAction(event -> handleDevolver());
        this.view.getBtnNovoLivro().setOnAction(event -> handleNovoLivro());
    }

    private void atualizarTabela() {
        List<Livros> livrosDoBanco = livroDAO.listarTodos();
        livrosObservableList.clear();
        livrosObservableList.addAll(livrosDoBanco);
    }

    private void handlePesquisar() {
        String termoBusca = view.getPesquisa();
        if (termoBusca == null || termoBusca.trim().isEmpty()) {
            atualizarTabela();
        } else {
            List<Livros> resultadoFiltro = livroDAO.buscarPorNome(termoBusca);
            livrosObservableList.clear();
            livrosObservableList.addAll(resultadoFiltro);
        }
    }

    private void handleEmprestar() {
        Livros livroSelecionado = view.getTabelaLivros().getSelectionModel().getSelectedItem();
        if (livroSelecionado == null) {
            exibirAlerta("Aviso", "Por favor, selecione um livro na tabela para emprestar.");
            return;
        }
        if ("Emprestado".equalsIgnoreCase(livroSelecionado.getStatus())) {
            exibirAlerta("Aviso", "Este livro já está emprestado!");
            return;
        }
        exibirAlerta("Empréstimo", "Você selecionou o livro: " + livroSelecionado.getTitulo());
    }

    private void handleDevolver() {
        Livros livroSelecionado = view.getTabelaLivros().getSelectionModel().getSelectedItem();
        if (livroSelecionado == null) {
            exibirAlerta("Aviso", "Por favor, selecione um livro na tabela para devolver.");
            return;
        }
        exibirAlerta("Devolução", "Devolvendo o livro: " + livroSelecionado.getTitulo());
    }

    private void handleNovoLivro() {
        view.CadastrarLivroView cadastroLivroView = new view.CadastrarLivroView();
        CadastrarLivroController cadastrarLivroController = new CadastrarLivroController(cadastroLivroView);

        javafx.scene.Scene novaCena = new javafx.scene.Scene(cadastroLivroView);
        // ... (código do CSS igual ao que você já tem)

        javafx.stage.Stage janelaAtual = (javafx.stage.Stage) view.getScene().getWindow();
        janelaAtual.setTitle("Sistema de Biblioteca ABA - Cadastrar Novo Livro");
        janelaAtual.setScene(novaCena);
        janelaAtual.centerOnScreen();
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}