package controller;

import view.BibliotecaView;
import view.LoginView;
import database.UsuarioDAO;
import model.Usuarios;
import javafx.scene.control.Alert;
import view.CadastroView;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        String emailDigitado = view.getEmail();
        String senhaDigitada = view.getSenha();

        if (emailDigitado.isEmpty() || java.util.Objects.isNull(senhaDigitada) || senhaDigitada.isEmpty()) {
            exibirAlerta("Campos Vazios", "Por favor, preencha o e-mail e a senha.");
            return;
        }

        Usuarios usuarioLogado = usuarioDAO.validarLogin(emailDigitado, senhaDigitada);

        if (usuarioLogado != null) {
            exibirAlerta("Sucesso", "Bem-vindo(a), " + usuarioLogado.getNome() + "!");

            BibliotecaView bibliotecaView = new BibliotecaView();

            BibliotecaController bibliotecaController = new BibliotecaController(bibliotecaView);

            Scene novaCena = new Scene(bibliotecaView);

            try {
                novaCena.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
            } catch (Exception e) {
                System.out.println("Aviso: CSS não encontrado para a biblioteca.");
            }

            Stage janelaAtual = (Stage) view.getScene().getWindow();
            janelaAtual.setTitle("Sistema de Biblioteca ABA - Gerenciamento de Acervo");
            janelaAtual.setScene(novaCena);
            janelaAtual.centerOnScreen(); 

        } else {
            exibirAlerta("Erro de Autenticação", "E-mail ou senha incorretos.");
        }
    }

    // muda pra tela de cadastro
    private void handleCadastrar() {
        CadastroView cadastroView = new CadastroView();
        CadastroController cadastroController = new CadastroController(cadastroView);
        Scene novaCena = new Scene(cadastroView);

        try {
            novaCena.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Aviso: CSS não encontrado para a tela de cadastro.");
        }

        Stage janelaAtual = (Stage) view.getScene().getWindow();

        janelaAtual.setTitle("Sistema de Biblioteca ABA - Cadastrar Usuário");
        janelaAtual.setScene(novaCena);
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}