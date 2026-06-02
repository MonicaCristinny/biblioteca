package database;

import model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categorias> listarTodas() {
        List<Categorias> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nome FROM categorias ORDER BY nome ASC";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categorias categoria = new Categorias(
                        rs.getInt("id_categoria"),
                        rs.getString("nome")
                );
                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todas as categorias.");
            e.printStackTrace();
        }
        return lista;
    }

    public int obterOuCadastrarCategoria(String nomeCategoria) {
        if (nomeCategoria == null || nomeCategoria.trim().isEmpty()) {
            return 0;
        }

        nomeCategoria = nomeCategoria.trim();

        String sqlBuscar = "SELECT id_categoria FROM categorias WHERE LOWER(nome) = LOWER(?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlBuscar)) {

            stmt.setString(1, nomeCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_categoria"); // Encontrou a categoria cadastrada!
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria (Case Insensitive).");
            e.printStackTrace();
        }

        String sqlInserir = "INSERT INTO categorias (nome) VALUES (?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlInserir, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nomeCategoria);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar nova categoria.");
            e.printStackTrace();
        }

        return 0;
    }
}