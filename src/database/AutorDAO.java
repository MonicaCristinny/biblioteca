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
        // CORRIGIDO: nome para nome_autor
        String sql = "SELECT id_autor, nome_autor FROM autores ORDER BY nome_autor ASC";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Autores autor = new Autores(
                        rs.getInt("id_autor"),
                        rs.getString("nome_autor") // CORRIGIDO
                );
                lista.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int obterOuCadastrarAutor(String nomeAutor) {
        if (nomeAutor == null || nomeAutor.trim().isEmpty()) {
            return 0;
        }
        nomeAutor = nomeAutor.trim();

        // CORRIGIDO: nome para nome_autor
        String sqlBuscar = "SELECT id_autor FROM autores WHERE LOWER(nome_autor) = LOWER(?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlBuscar)) {

            stmt.setString(1, nomeAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_autor");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar autor no banco:");
            e.printStackTrace();
        }

        // CORRIGIDO: nome para nome_autor
        String sqlInserir = "INSERT INTO autores (nome_autor) VALUES (?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlInserir, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nomeAutor);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir novo autor no banco:");
            e.printStackTrace();
        }

        return 0;
    }
}