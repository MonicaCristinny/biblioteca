package controller;

import view.LoginView;
import javafx.scene.control.Alert;

public class LoginController {

    private LoginView view;
    public LoginController(LoginView view){
        this.view = view;

        this.view.getBtnEntrar().setOnAction(event -> handleEntrar());
        this.view.getBtnCadastrar().setOnAction(event -> handleCadastrar());
    }

    private void handleEntrar(){
        String usuario = view.getUsuario();
        String senha = view.getSenha();

        if (usuario.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos Vazios", "Por favor, preencha o usuário e a senha.");
            return;
        }

        // Ponto de Teste Temporário: Como ainda não conectamos o Banco de Dados,
        // vamos criar um usuário "admin" estático apenas para testar se a nossa tela funciona.
        if (usuario.equals("admin") && senha.equals("1234")) {
            exibirAlerta("Sucesso", "Login efetuado com sucesso!");

            // Futuramente, colocaremos aqui o código para fechar essa tela
            // e abrir a tela principal de livros (LivroView).
        } else {
            exibirAlerta("Erro de Autenticação", "Usuário ou senha incorretos.");
    }
}
