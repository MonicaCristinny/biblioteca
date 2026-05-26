package model;

/* criando classe de usuarios */


public class Usuario {
    private int id_usuario;
    private String nome;
    private String email;
    private String senha;


    /* modo construtor */
    public Usuario(int id_usuario, String nome, String email, String senha){

        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }


    /* definindo get's e set's */
    public int getId_usuario(){
        return id_usuario;
    }

    public void setId_usuario(int id_usuario){
        this.id_usuario = id_usuario;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
