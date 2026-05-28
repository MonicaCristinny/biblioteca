package components;

import javafx.scene.control.*;

public class ComponentFactory {

    public static Label criarTexto (String texto) {
         Label titulo = new Label(texto);

         titulo.getStyleClass().add("estilo-texto");

         return titulo;

    }

    public static TextField criarCampo(String textoCampo){
        TextField campo = new TextField("textoCampo");

        campo.setPromptText(textoCampo);

        campo.setPrefWidth(250);

        campo.getStyleClass().add("estilo-campo");

        return campo;

    }

    public static Button criarBotao(String texto, Integer largura){
        Button botao = new Button(texto);

        botao.setPrefWidth(largura);

        botao.getStyleClass().add("estilo-botao");

        return botao;
    }

    public static PasswordField criarSenha(String prompt) {
        PasswordField senha = new PasswordField();

        senha.setPromptText(prompt);

        senha.setPrefWidth(250);

        senha.getStyleClass().add("estilo-semha");

        return senha;
    }

}
