package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Livros;
import view.BibliotecaView;
import view.CadastrarLivroView;
import database.LivroDAO;
import database.AutorDAO;
import database.CategoriaDAO;

public class CadastrarLivroController {
    private CadastrarLivroView view;
    private LivroDAO livroDAO;
    private AutorDAO autorDAO;
    private CategoriaDAO categoriaDAO;

    public CadastrarLivroController(CadastrarLivroView view) {
        this.view = view;
        this.livroDAO = new LivroDAO();
        this.autorDAO = new AutorDAO();
        this.categoriaDAO = new CategoriaDAO();

        this.view.getBtnSalvar().setOnAction(e -> handlesalvar());

        this.view.getBtnVoltar().setOnAction(e -> handleVoltar());
    }

    public void handlesalvar() {
        String titulo = view.getTitulo();
        String categoriaTexto = view.getCategoria();
        String autorTexto = view.getAutor();

        if (titulo.isEmpty() || categoriaTexto.isEmpty() || autorTexto.isEmpty()) {
            exibirAlerta("Erro", "Por favor, preencha todos os campos.");
            return;
        }

        try {
            int idCategoria = categoriaDAO.obterOuCadastrarCategoria(categoriaTexto);
            int idAutor = autorDAO.obterOuCadastrarAutor(autorTexto);

            // Verificação de segurança caso as DAOs falhem internamente
            if (idCategoria == 0 || idAutor == 0) {
                exibirAlerta("Erro no Banco", "Falha ao processar a Categoria ou o Autor no banco de dados.");
                return;
            }

            Livros livro = new Livros(0, titulo, idCategoria, idAutor, "Disponível");

            boolean sucesso = this.livroDAO.salvar(livro);

            if (sucesso) {
                exibirAlerta("Sucesso", "Livro '" + titulo + "' cadastrado com sucesso!");
            } else {
                exibirAlerta("Erro", "Falha ao salvar o livro no banco de dados.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao processar salvamento do livro:");
            e.printStackTrace();
            exibirAlerta("Erro de Sistema", "Não foi possível realizar a operação.");
        }
    }

    private void handleVoltar() {
        // Cria a tela principal
        BibliotecaView bibliotecaView = new BibliotecaView();
        BibliotecaController bibliotecaController = new BibliotecaController(bibliotecaView);

        // Prepara a cena com os estilos
        Scene cenaBiblioteca = new Scene(bibliotecaView);
        try {
            cenaBiblioteca.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Aviso: CSS não encontrado ao retornar para a biblioteca.");
        }

        // Pega a janela (Stage) atual e substitui a cena
        Stage janelaAtual = (Stage) view.getScene().getWindow();
        janelaAtual.setTitle("Sistema de Biblioteca ABA - Gerenciamento de Acervo");
        janelaAtual.setScene(cenaBiblioteca);
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