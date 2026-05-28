package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

public class LoginView extends VBox {
    private TextField txtUsuario;
    private PasswordField txtSenha;
    private Button btnEntrar;
    private Button btnCadastrar;

    public LoginView(){
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20.0);
        this.setPadding(new Insets(40));
        this.setPrefSize(500,400);
    }

    Label titulo = new Label("Biblioteca ABA");

    VBox boxUsuario = new VBox(5);
    Label usuario = new Label("Usuário/Login:");
    txtUsuario = new TextField();
    txtUsuario.setPromptText("Digite seu usuário");

    Vbox boxSenha = new VBox(5);
    Label senha = new Label("Senha:");
    txtSenha = new PasswordField();
    boxSenha.getChildren().addAll(senha, txtSenha);

    HBox boxBotoes = new HBox(20);
    boxBotoes.setAlignment(Pos.CENTER);

    btnEntrar = new Button("Entrar");
    btnEntrar.setPrefWidth(120);

    btnCadastrar = new Button("Cadastrar");
    btnCadastrar.setPrefWidth(120);

    boxBotoes.getChildren().addAll(btnEntrar, btnCadastrar);

    this.getChildren().addAll(titulo, boxUsuario, boxSenha, boxBotoes);

    public String getUsuario() { return txtUsuario.getText(); }
    public String getSenha() { return txtSenha.getText(); }

    public Button getBtnEntrar() { return btnEntrar; }
    public Button getBtnCadastrar() { return btnCadastrar; }
}
