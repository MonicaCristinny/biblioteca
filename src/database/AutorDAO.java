package database;

import model.Autores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    public List<Autores> listarTodos() {
        List<Autores> lista = new ArrayList<>();
        String sql = "SELECT id_autor, nome FROM autores ORDER BY nome ASC";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Cria o modelo baseado nas colunas do banco
                Autores autor = new Autores(
                        rs.getInt("id_autor"),
                        rs.getString("nome")
                );
                lista.add(autor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os autores.");
            e.printStackTrace();
        }
        return lista;
    }

    public int obterOuCadastrarAutor(String nomeAutor) {
        if (nomeAutor == null || nomeAutor.trim().isEmpty()) {
            return 0;
        }
        nomeAutor = nomeAutor.trim();

        String sqlBuscar = "SELECT id_autor FROM autores WHERE LOWER(nome) = LOWER(?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlBuscar)) {

            stmt.setString(1, nomeAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_autor"); // Encontrou o autor cadastrado!
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar autor (Case Insensitive).");
            e.printStackTrace();
        }

        String sqlInserir = "INSERT INTO autores (nome) VALUES (?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlInserir, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nomeAutor);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar novo autor.");
            e.printStackTrace();
        }

        return 0;
    }
}