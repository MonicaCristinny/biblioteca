package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        LoginView view = new LoginView(stage);

        Scene scene = new Scene(view.getView(), 400, 300);

        scene.getStylesheets().add(
                getClass().getResource("/style/login.css").toExternalForm()
        );

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}