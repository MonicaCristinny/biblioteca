package controller;

import javafx.scene.control.Alert;
import model.Livros;
import view.CadastrarLivroView;
import view.BibliotecaView;
import database.LivroDAO;
import database.AutorDAO;
import database.CategoriaDAO;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastrarLivroController {
    private CadastrarLivroView view;
    private LivroDAO livroDAO;
    private AutorDAO autorDAO;
    private CategoriaDAO categoriaDAO;

    public CadastrarLivroController(CadastrarLivroView view){
        this.view = view;
        this.livroDAO = new LivroDAO();
        this.autorDAO = new AutorDAO();
        this.categoriaDAO = new CategoriaDAO();

        this.view.getBtnSalvar().setOnAction(e -> handlesalvar(view));
        this.view.getBtnVoltar().setOnAction(e -> handleVoltar());
    }

    public void handlesalvar(CadastrarLivroView view){
        String tituloDigitado = view.getTitulo();
        String nomeCategoria = view.getCategoria();
        String nomeAutor = view.getAutor();

        String statusPadrao = "Disponível";

        if (tituloDigitado.isEmpty() || nomeCategoria.isEmpty() || nomeAutor.isEmpty()){
            exibirAlerta("Erro", "Preencha todos os campos.");
            return;
        }

        try {
            int idCategoria = categoriaDAO.obterOuCadastrarCategoria(nomeCategoria);
            int idAutor = autorDAO.obterOuCadastrarAutor(nomeAutor);

            if (idCategoria == 0 || idAutor == 0) {
                exibirAlerta("Erro", "Falha ao processar a Categoria ou o Autor no banco de dados.");
                return;
            }

            Livros livro = new Livros(0, tituloDigitado, idCategoria, idAutor, statusPadrao);
            boolean sucesso = this.livroDAO.salvar(livro);

            if (sucesso) {
                exibirAlerta("Sucesso", "Livro '" + tituloDigitado + "' cadastrado com sucesso!");
                handleVoltar();
            } else {
                exibirAlerta("Erro", "Falha ao salvar o livro no banco de dados.");
            }

        } catch (Exception e) {
            exibirAlerta("Erro Inesperado", "Ocorreu um problema ao salvar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleVoltar() {
        BibliotecaView bibliotecaView = new BibliotecaView();
        BibliotecaController bibliotecaController = new BibliotecaController(bibliotecaView);

        Scene cenaBiblioteca = new Scene(bibliotecaView);
        try {
            cenaBiblioteca.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Aviso: CSS não encontrado ao retornar para a biblioteca.");
        }

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