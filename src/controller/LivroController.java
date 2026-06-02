package controller;

import database.AutorDAO;
import database.CategoriaDAO;
import database.LivroDAO;
import model.Autores;
import model.Categorias;
import model.Livros;
import java.util.List;

public class LivroController {

    private LivroDAO livroDAO;
    private AutorDAO autorDAO;
    private CategoriaDAO categoriaDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
        this.autorDAO = new AutorDAO();
        this.categoriaDAO = new CategoriaDAO();
    }

    public List<Autores> listarAutores() {
        return autorDAO.listarTodos();
    }

    public List<Categorias> listarCategorias(){
        return categoriaDAO.listarTodas();
    }

    // Botão Pesquisa
    public List<Livros> pesquisarLivros(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return livroDAO.listarTodos();
        }
        return livroDAO.buscarPorNome(titulo);
    }

    public boolean cadastrarNovoLivro(String titulo, String nomeCategoria, String nomeAutor) {
        if (titulo == null || titulo.trim().isEmpty() ||
                nomeCategoria == null || nomeCategoria.trim().isEmpty() ||
                nomeAutor == null || nomeAutor.trim().isEmpty()) {
            return false;
        }

        try {
            int idCategoria = categoriaDAO.obterOuCadastrarCategoria(nomeCategoria);
            int idAutor = autorDAO.obterOuCadastrarAutor(nomeAutor);

            if (idCategoria == 0 || idAutor == 0) {
                return false;
            }

            Livros livro = new Livros(0, titulo.trim(), idCategoria, idAutor, "Disponível");

            return livroDAO.salvar(livro);

        } catch (Exception e) {
            System.out.println("Erro ao processar o cadastro do livro no controlador: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Botão Excluir
    public boolean deletarLivro(int idLivro) {
        return livroDAO.excluir(idLivro);
    }
}