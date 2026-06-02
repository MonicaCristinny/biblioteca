package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import components.ComponentFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CadastrarLivroView extends VBox {

    private TextField txtTitulo = new TextField();
    private TextField txtCategoria = new TextField();
    private TextField txtAutor = new TextField();
    // REMOVIDO: txtStatus não é mais um atributo editável

    public CadastrarLivroView() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15.0);
        this.setPadding(new Insets(30));
        this.setPrefSize(500, 400);
        this.getStyleClass().add("cadastro-container");

        Label TituloInicio = ComponentFactory.criarTexto("Cadastro de Livros");

        VBox boxTitulo = new VBox(5);
        Label lblTitulo = ComponentFactory.criarTexto("Título:");
        txtTitulo = ComponentFactory.criarCampo("Digite o título da obra");
        boxTitulo.getChildren().addAll(lblTitulo, txtTitulo);

        VBox boxCategoria = new VBox(5);
        Label lblCategoria = ComponentFactory.criarTexto("Categoria (Nome):");
        txtCategoria = ComponentFactory.criarCampo("Ex: Romance, Terror, Ficção");
        boxCategoria.getChildren().addAll(lblCategoria, txtCategoria);

        VBox boxAutor = new VBox(5);
        Label lblAutor = ComponentFactory.criarTexto("Autor (Nome):");
        txtAutor = ComponentFactory.criarCampo("Digite o nome completo do autor");
        boxAutor.getChildren().addAll(lblAutor, txtAutor);


        HBox boxBotoes = new HBox(15);
        boxBotoes.setAlignment(Pos.CENTER);
        Button btnSalvar = ComponentFactory.criarBotao("Salvar", 120);
        Button btnVoltar = ComponentFactory.criarBotao("Voltar", 120);

        this.btnSalvar = btnSalvar;
        this.btnVoltar = btnVoltar;

        boxBotoes.getChildren().addAll(btnSalvar, btnVoltar);

        this.getChildren().addAll(TituloInicio, boxTitulo, boxCategoria, boxAutor, boxBotoes);
    }

    private Button btnSalvar;
    private Button btnVoltar;

    public String getTitulo() { return txtTitulo.getText(); }
    public String getCategoria() { return txtCategoria.getText(); }
    public String getAutor() { return txtAutor.getText(); }
    public Button getBtnSalvar() { return btnSalvar; }
    public Button getBtnVoltar() { return btnVoltar; }
}