package model;

/* criando classe de livros */

public class Livro {
    private int id_livro;
    private String titulo;
    private int id_categoria;
    private int id_autor;
    private enum status;


    /* metodo contrutor */

    public Livro(int id_livros, String titulo, int id_categoria, int id_autor){

        this.id_livro = id_livros;
        this.titulo = titulo;
        this.id_categoria = id_categoria;
        this.id_autor = id_autor;

    }


    /* definindo get's e set's */
    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }
}

