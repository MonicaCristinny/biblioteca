package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.BibliotecaView;
import view.LoginView;
import database.UsuarioDAO;
import model.Usuarios;

public class LoginController {

    private LoginView view;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();

        this.view.getBtnEntrar().setOnAction(event -> handleEntrar());
    }

    private void handleEntrar() {
        String emailDigitado = view.getEmail();
        String senhaDigitada = view.getSenha();

        if (emailDigitado.isEmpty() || senhaDigitada.isEmpty()) {
            exibirAlertaErro("Erro", "Por favor, preencha todos os campos.");
            return;
        }

        model.Usuarios usuarioLogado = usuarioDAO.validarLogin(emailDigitado, senhaDigitada);

        if (usuarioLogado != null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bem-vindo");
            alert.setHeaderText(null);
            alert.setContentText("Login realizado com sucesso! Carregando acervo...");

            try {
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("meu-alerta-customizado");
            } catch (Exception e) {
                System.out.println("Aviso: CSS do alerta não carregado.");
            }

            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.seconds(2),
                            ae -> {
                                alert.close();

                                BibliotecaView bibliotecaView = new BibliotecaView();
                                BibliotecaController bibliotecaController = new BibliotecaController(bibliotecaView);

                                Scene cena = new Scene(bibliotecaView);
                                try {
                                    cena.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
                                } catch (Exception e) {
                                    System.out.println("Aviso: CSS da biblioteca não encontrado.");
                                }

                                Stage stage = (Stage) view.getScene().getWindow();
                                stage.setScene(cena);
                                stage.setTitle("Sistema de Biblioteca ABA - Gerenciamento de Acervo");
                                stage.centerOnScreen();
                            }
                    )
            );
            timeline.setCycleCount(1);
            timeline.play();

            alert.show();

        } else {
            exibirAlertaErro("Erro de Autenticação", "E-mail ou senha incorretos.");
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        try {
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        } catch (Exception e) {
        }
        alert.showAndWait();
    }
}