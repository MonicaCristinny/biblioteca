package controller;

import javafx.scene.control.Alert;
import model.Livros;
import view.CadastrarLivroView;
import view.BibliotecaView;
import database.LivroDAO;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastrarLivroController {
    private CadastrarLivroView view;
    private String titulo;
    private String categoria;
    private String autor;
    private String status;
    private LivroDAO livroDAO;

    public CadastrarLivroController(CadastrarLivroView view){
        this.view = view;
        this.livroDAO = new LivroDAO();

        // Configura os gatilhos dos botões
        this.view.getBtnSalvar().setOnAction(e -> handlesalvar(view));
        this.view.getBtnVoltar().setOnAction(e -> handleVoltar());
    }

    public void handlesalvar(CadastrarLivroView view){
        this.titulo = view.getTitulo();
        this.categoria = view.getCategoria();
        this.autor = view.getAutor();
        this.status = view.getStatus();

        if (titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty()){
            exibirAlerta("Erro", "Preencha todos os campos");
            return;
        }
        try {
            int idCategoria = Integer.parseInt(categoria);
            int idAutor = Integer.parseInt(autor);

            Livros livro = new Livros(0, titulo, idCategoria, idAutor, status);
            boolean sucesso = this.livroDAO.salvar(livro);

            if (sucesso) {
                exibirAlerta("Sucesso", "Livro cadastrado com sucesso!");
                handleVoltar(); // Opcional: Volta para a biblioteca automaticamente após salvar
            } else {
                exibirAlerta("Erro", "Falha ao cadastrar livro no banco de dados.");
            }

        } catch (NumberFormatException e) {
            exibirAlerta("Erro de Formato", "Os campos Categoria e Autor devem ser números (IDs).");
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