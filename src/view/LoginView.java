package view;

import components.ComponentFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;       // Import correto do JavaFX
import javafx.scene.control.Label;        // Import correto do JavaFX
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;    // Import correto do JavaFX
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {

    private TextField txtEmail;
    private PasswordField txtSenha;
    private Button btnEntrar;
    private Button btnCadastrar;

    public LoginView() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);
        this.setPadding(new Insets(40));
        this.setPrefSize(500, 400);
        this.getStyleClass().add("login-container");

        Label lblTitulo = ComponentFactory.criarTexto("Biblioteca ABA");

        VBox boxEmail = new VBox(5);
        Label lblEmail = ComponentFactory.criarTexto("E-mail:");
        txtEmail = ComponentFactory.criarCampo("Digite seu e-mail");
        boxEmail.getChildren().addAll(lblEmail, txtEmail);

        VBox boxSenha = new VBox(5);
        Label lblSenha = ComponentFactory.criarTexto("Senha:");
        txtSenha = ComponentFactory.criarSenha("Digite sua senha");
        boxSenha.getChildren().addAll(lblSenha, txtSenha);

        HBox boxBotoes = new HBox(20);
        boxBotoes.setAlignment(Pos.CENTER);
        boxBotoes.setPadding(new Insets(15, 0, 0, 0));

        btnEntrar = ComponentFactory.criarBotao("Entrar", 120);
        btnCadastrar = ComponentFactory.criarBotao("Cadastrar", 120);
        boxBotoes.getChildren().addAll(btnEntrar, btnCadastrar);


        this.getChildren().addAll(lblTitulo, boxEmail, boxSenha, boxBotoes);
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getSenha() {
        return txtSenha.getText();
    }

    public Button getBtnEntrar() {
        return btnEntrar;
    }

    public Button getBtnCadastrar() {
        return btnCadastrar;
    }
}