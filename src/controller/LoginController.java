package controller;

import view.LoginView;
import database.UsuarioDAO;
import model.Usuario;
import javafx.scene.control.Alert;

public class LoginController {

    private LoginView view;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();

        this.view.getBtnEntrar().setOnAction(event -> handleEntrar());
        this.view.getBtnCadastrar().setOnAction(event -> handleCadastrar());
    }

    private void handleEntrar() {
        String emailDigitado = view.getUsuario();
        String senhaDigitada = view.getSenha();

        if (emailDigitado.isEmpty() || java.util.Objects.isNull(senhaDigitada) || senhaDigitada.isEmpty()) {
            exibirAlerta("Campos Vazios", "Por favor, preencha o e-mail e a senha.");
            return;
        }

        Usuario usuarioLogado = usuarioDAO.validarLogin(emailDigitado, senhaDigitada);

        if (usuarioLogado != null) {
            exibirAlerta("Sucesso", "Bem-vindo(a), " + usuarioLogado.getNome() + "!");

        } else {
            exibirAlerta("Erro de Autenticação", "E-mail ou senha incorretos.");
        }
    }

    private void handleCadastrar() {
        exibirAlerta("Cadastro", "A tela de cadastro utilizará o modelo da classe Usuario.");
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}