package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import components.ComponentFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class CadastrarLivroView extends VBox{

    private TextField txtTitulo = new TextField();
    private TextField txtCategoria = new TextField();
    private TextField txtAutor = new TextField();
    private TextField txtStatus = new TextField();
    private Button btnSalvar = new Button();
    private Button btnVoltar = new Button();

    public CadastrarLivroView() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15.0);
        this.setPadding(new Insets(30));
        this.setPrefSize(500, 400);
        this.getStyleClass().add("cadastro-container");

        Label TituloInicio = ComponentFactory.criarTexto("Cadastro de Livros");

        VBox boxTitulo = new VBox(5);
        Label lblTitulo = ComponentFactory.criarTexto("Título:");
        txtTitulo = ComponentFactory.criarCampo("Digite o titulo da obra");
        boxTitulo.getChildren().addAll(lblTitulo, txtTitulo);

        VBox boxCategoria = new VBox(5);
        Label lblCategoria = ComponentFactory.criarTexto("Categoria:");
        txtCategoria = ComponentFactory.criarCampo("Digite a categoria");
        boxCategoria.getChildren().addAll(lblCategoria, txtCategoria);

        VBox boxAutor = new VBox(5);
        Label lblAutor = ComponentFactory.criarTexto("Autor:");
        txtAutor = ComponentFactory.criarCampo("Digite o nome do autor");
        boxAutor.getChildren().addAll(lblAutor, txtAutor);

        VBox boxStatus = new VBox(5);
        Label lblStatus = ComponentFactory.criarTexto("Status:");
        txtStatus = ComponentFactory.criarCampo("Digite o estado do livro");
        boxStatus.getChildren().addAll(lblStatus, txtStatus);

        HBox boxBotoes = new HBox();
        btnSalvar = ComponentFactory.criarBotao("Salvar", 120);
        btnVoltar = ComponentFactory.criarBotao("Voltar", 120);
        boxBotoes.getChildren().addAll(btnSalvar, btnVoltar);

        boxBotoes.getChildren().addAll(btnSalvar, btnSalvar);
        this.getChildren().addAll(TituloInicio, boxTitulo, boxCategoria, boxAutor, boxStatus, boxBotoes);

        }

    public String getTitulo() { return txtTitulo.getText(); }
    public String getCategoria() { return txtCategoria.getText(); }
    public String getAutor() { return txtAutor.getText(); }
    public String getStatus() { return txtStatus.getText(); }
    public Button getBtnSalvar() { return btnSalvar; }
    public Button getBtnVoltar() { return btnVoltar; }
}

