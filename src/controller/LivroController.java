package controller;

import database.AutorDAO;
import database.CategoriaDAO;
import database.LivroDAO;
import model.Autor;
import model.Categoria;
import model.Livro;
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

    public List<Autor> listarAutores() {
        return autorDAO.listarTodos();
    }

    public List<Categoria> listarCategorias(){
        return categoriaDAO.listarTodas();
    }

    // botao pesquisa
    public List<Livro> pesquisarLivros(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return livroDAO.listarTodos();
        }
        return livroDAO.buscarPorNome(titulo);
    }

    // botao cadastrar
    public boolean cadastrarNovoLivro(String titulo, int idCategoria, int idAutor) {
        if (titulo.isEmpty() || idCategoria <= 0 || idAutor <= 0) {
            return false;
        }
        // Passa 0 no ID porque o MySQL gera o ID automaticamente (AUTO_INCREMENT)
        Livro livro = new Livro(0, titulo, idCategoria, idAutor);
        return livroDAO.salvar(livro);
    }

    // botao excluir
    public boolean deletarLivro(int idLivro) {
        return livroDAO.excluir(idLivro);
    }
}