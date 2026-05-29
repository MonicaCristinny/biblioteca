package view;

import components.ComponentFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CadastroView extends VBox {

    private TextField txtNome;
    private TextField txtEmail;
    private PasswordField txtSenha;
    private Button btnSalvar;
    private Button btnVoltar;

    public CadastroView() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15.0);
        this.setPadding(new Insets(30));
        this.setPrefSize(500, 400);
        this.getStyleClass().add("cadastro-container");

        Label lblTitulo = ComponentFactory.criarTexto("Cadastro de Usuário");

        VBox boxNome = new VBox(5);
        Label lblNome = ComponentFactory.criarTexto("Nome Completo:");
        txtNome = ComponentFactory.criarCampo("Digite o nome do usuário");
        boxNome.getChildren().addAll(lblNome, txtNome);

        VBox boxEmail = new VBox(5);
        Label lblEmail = ComponentFactory.criarTexto("E-mail:");
        txtEmail = ComponentFactory.criarCampo("Digite o e-mail");
        boxEmail.getChildren().addAll(lblEmail, txtEmail);

        VBox boxSenha = new VBox(5);
        Label lblSenha = ComponentFactory.criarTexto("Senha:");
        txtSenha = ComponentFactory.criarSenha("Crie uma senha");
        boxSenha.getChildren().addAll(lblSenha, txtSenha);

        HBox boxBotoes = new HBox(20);
        boxBotoes.setAlignment(Pos.CENTER);
        boxBotoes.setPadding(new Insets(15, 0, 0, 0));

        btnSalvar = ComponentFactory.criarBotao("Salvar", 120);
        btnVoltar = ComponentFactory.criarBotao("Voltar", 120);
        boxBotoes.getChildren().addAll(btnSalvar, btnVoltar);

        this.getChildren().addAll(lblTitulo, boxNome, boxEmail, boxSenha, boxBotoes);
    }

    // GETTERS: Para o controlador conseguir ler o que foi digitado e interagir com os botões
    public String getNome() { return txtNome.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getSenha() { return txtSenha.getText(); }
    public Button getBtnSalvar() { return btnSalvar; }
    public Button getBtnVoltar() { return btnVoltar; }
}