package database;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Database.Conexao;

public class UsuarioDAO {

    // verifica se o usuario existe
    public Usuario validarLogin(String email, String senha) {

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao validar login no banco.");
            e.printStackTrace();
        }
        return null;
    }
}