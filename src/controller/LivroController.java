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

    // botao pesquisa
    public List<Livros> pesquisarLivros(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return livroDAO.listarTodos();
        }
        return livroDAO.buscarPorNome(titulo);
    }

    // botao cadastrar
    public boolean cadastrarNovoLivro(String titulo, int idCategoria, int idAutor, String status) {
        if (titulo.isEmpty() || idCategoria <= 0 || idAutor <= 0) {
            return false;
        }
        // Passa 0 no ID porque o MySQL gera o ID automaticamente (AUTO_INCREMENT)
        Livros livro = new Livros(0, titulo, idCategoria, idAutor, status);
        return livroDAO.salvar(livro);
    }

    // botao excluir
    public boolean deletarLivro(int idLivro) {
        return livroDAO.excluir(idLivro);
    }
}