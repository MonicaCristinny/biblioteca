package controller;

import javafx.scene.control.Alert;
import model.Livros;
import view.CadastrarLivroView;
import database.LivroDAO;


public class CadastroLivroController {
    CadastrarLivroView view;
    String titulo;
    String categoria;
    String autor;
    String status;
    LivroDAO livroDAO;

    public CadastroLivroController(CadastrarLivroView view){
        this.view = view;
        this.livroDAO = new LivroDAO();

        this.view.getBtnSalvar().setOnAction( e -> handlesalvar(view));
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
        try{
            int idCategoria = Integer.parseInt(categoria);
            int idAutor = Integer.parseInt(autor);

            Livros livro = new Livros(0, titulo, idCategoria, idAutor, status);

            boolean sucesso = this.livroDAO.salvar(livro);

        if (sucesso) {
            System.out.println("Livro cadastrado com sucesso no banco de dados");
        } else {
            System.out.println("Falha ao cadastrar livro no banco de dados");
        }

        } catch (NumberFormatException e) {
            exibirAlerta("Erro de Formato", "Os campos Categoria e Autor devem ser números (IDs).");
        }

    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
