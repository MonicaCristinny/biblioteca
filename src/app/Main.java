package app;

import controller.LoginController;
import view.LoginView;
import view.BibliotecaView;
import view.CadastrarLivroView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BibliotecaView bibliotecaview = new BibliotecaView();
        CadastrarLivroView cadastrarLivroView = new CadastrarLivroView();
        System.out.println("1");
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);

        Scene cena = new Scene(cadastrarLivroView);
        System.out.println("2");

        try {
            cena.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Aviso: Arquivo style.css não encontrado ou vazio. Carregando layout padrão.");
        }

        primaryStage.setTitle("Sistema de Biblioteca ABA - Login");
        primaryStage.setScene(cena);
        primaryStage.setResizable(false);
        primaryStage.show();
        System.out.println("3");

    }

    public static void main(String[] args) {
        launch(args);
    }
}


