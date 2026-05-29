package controller;

import database.UsuarioDAO;
import model.Usuarios;
import view.CadastroView;
import view.LoginView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CadastroController {

    private CadastroView view;
    private UsuarioDAO usuarioDAO;

    public CadastroController(CadastroView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        this.view.getBtnSalvar().setOnAction(event -> handleSalvar());
        this.view.getBtnVoltar().setOnAction(event -> handleVoltar());
    }

    private void handleSalvar() {
        String nome = view.getNome();
        String email = view.getEmail();
        String senha = view.getSenha();

        // 1. Validação básica: impede campos em branco
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos Vazios", "Por favor, preencha todos os campos do cadastro.");
            return;
        }

        // 2. Empacota os dados no Modelo 'Usuarios' (Passa 0 no ID pois o banco gera com AUTO_INCREMENT)
        Usuarios novoUsuario = new Usuarios(0, nome, email, senha);

        // 3. Tenta salvar no banco de dados usando o seu DAO
        boolean sucesso = usuarioDAO.salvar(novoUsuario);
        // Nota: Certifique-se de que seu UsuarioDAO possui o método salvar(Usuarios u) pronto.

        if (sucesso) {
            exibirAlerta("Sucesso", "Usuário cadastrado com sucesso!");
            handleVoltar();
        } else {
            exibirAlerta("Erro no Banco", "Não foi possível realizar o cadastro. Tente novamente.");
        }
    }

    private void handleVoltar() {
        LoginView loginView = new LoginView();

        LoginController loginController = new LoginController(loginView);

        Scene cenaLogin = new Scene(loginView);
        try {
            cenaLogin.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Aviso: CSS não encontrado ao voltar para o login.");
        }

        Stage janelaAtual = (Stage) view.getScene().getWindow();
        janelaAtual.setTitle("Sistema de Biblioteca ABA - Login");
        janelaAtual.setScene(cenaLogin);
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}