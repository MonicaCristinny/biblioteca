package view;

import components.ComponentFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Livros;

public class BibliotecaView extends VBox {

    private TextField txtPesquisa;
    private Button btnPesquisar;
    private Button btnEmprestar;
    private Button btnDevolver;
    private Button btnNovoLivro;
    private TableView<Livros> tabelaLivros;

    public BibliotecaView() {
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20.0);
        this.setPadding(new Insets(30));
        this.setPrefSize(800, 600);
        this.getStyleClass().add("biblioteca-container");

        Label lblTitulo = ComponentFactory.criarTexto("Acervo da Biblioteca ABA");

        HBox boxPesquisa = new HBox(10);
        boxPesquisa.setAlignment(Pos.CENTER_LEFT);

        txtPesquisa = ComponentFactory.criarCampo("Digite o título do livro para pesquisar...");
        txtPesquisa.setPrefWidth(450);

        btnPesquisar = ComponentFactory.criarBotao("Pesquisar", 120);
        boxPesquisa.getChildren().addAll(txtPesquisa, btnPesquisar);

        tabelaLivros = new TableView<>();
        tabelaLivros.setPrefHeight(350);
        tabelaLivros.getStyleClass().add("tabela-livros");

        // organiza colunas da tabela
        TableColumn<Livros, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id_livros"));
        colId.setPrefWidth(60);

        TableColumn<Livros, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colTitulo.setPrefWidth(280);

        TableColumn<Livros, Integer> colCategoria = new TableColumn<>("ID Categoria");
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        colCategoria.setPrefWidth(100);

        TableColumn<Livros, Integer> colAutor = new TableColumn<>("ID Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<>("id_autor"));
        colAutor.setPrefWidth(100);

        TableColumn<Livros, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setPrefWidth(120);

        tabelaLivros.getColumns().addAll(colId, colTitulo, colCategoria, colAutor, colStatus);

        HBox boxAcoes = new HBox(15);
        boxAcoes.setAlignment(Pos.CENTER_RIGHT);

        btnEmprestar = ComponentFactory.criarBotao("Emprestar", 130);
        btnDevolver = ComponentFactory.criarBotao("Devolver", 130);
        btnNovoLivro = ComponentFactory.criarBotao("Novo Livro", 130);

        boxAcoes.getChildren().addAll(btnEmprestar, btnDevolver, btnNovoLivro);

        this.getChildren().addAll(lblTitulo, boxPesquisa, tabelaLivros, boxAcoes);
    }

    public String getPesquisa() { return txtPesquisa.getText(); }
    public Button getBtnPesquisar() { return btnPesquisar; }
    public Button getBtnEmprestar() { return btnEmprestar; }
    public Button getBtnDevolver() { return btnDevolver; }
    public Button getBtnNovoLivro() { return btnNovoLivro; }
    public TableView<Livros> getTabelaLivros() { return tabelaLivros; }
}