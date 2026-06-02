package controller;

import database.LivroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Livros;
import view.BibliotecaView;

import java.util.List;

public class BibliotecaController {

    private BibliotecaView view;
    private LivroDAO livroDAO;

    // atualiza a tabela automaticamente
    private ObservableList<Livros> livrosObservableList;

    public BibliotecaController(BibliotecaView view) {
        this.view = view;
        this.livroDAO = new LivroDAO();
        this.livrosObservableList = FXCollections.observableArrayList();

        // Vincula a lista à tabela da View
        this.view.getTabelaLivros().setItems(livrosObservableList);

        // Busca todos os livros
        this.atualizarTabela();

        // Configura o clique dos
        this.view.getBtnPesquisar().setOnAction(event -> handlePesquisar());
        this.view.getBtnEmprestar().setOnAction(event -> handleEmprestar());
        this.view.getBtnDevolver().setOnAction(event -> handleDevolver());
        this.view.getBtnNovoLivro().setOnAction(event -> handleNovoLivro());
    }

    //busca os livros
    private void atualizarTabela() {
        List<Livros> livrosDoBanco = livroDAO.listarTodos();
        livrosObservableList.clear();
        livrosObservableList.addAll(livrosDoBanco);
    }

    // Ação do Botão Pesquisar
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

    //Botão Emprestar
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

        exibirAlerta("Empréstimo", "Você selecionou o livro: " + livroSelecionado.getTitulo() + "\n(Lógica de alteração de status pode ser aplicada aqui).");
    }

    // Botão Devolver
    private void handleDevolver() {
        Livros livroSelecionado = view.getTabelaLivros().getSelectionModel().getSelectedItem();

        if (livroSelecionado == null) {
            exibirAlerta("Aviso", "Por favor, selecione um livro na tabela para devolver.");
            return;
        }

        exibirAlerta("Devolução", "Devolvendo o livro: " + livroSelecionado.getTitulo());
    }

    // Botão Novo Livro
    private void handleNovoLivro() {
        exibirAlerta("Novo Livro", "Aqui vocês podem abrir a tela 'CadastrarLivroView' que criaram!");
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}