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
        // CORRIGIDO: nome para nome_categoria
        String sql = "SELECT id_categoria, nome_categoria FROM categorias ORDER BY nome_categoria ASC";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categorias categoria = new Categorias(
                        rs.getInt("id_categoria"),
                        rs.getString("nome_categoria") // CORRIGIDO
                );
                lista.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int obterOuCadastrarCategoria(String nomeCategoria) {
        if (nomeCategoria == null || nomeCategoria.trim().isEmpty()) {
            return 0;
        }
        nomeCategoria = nomeCategoria.trim();

        // CORRIGIDO: nome para nome_categoria
        String sqlBuscar = "SELECT id_categoria FROM categorias WHERE LOWER(nome_categoria) = LOWER(?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlBuscar)) {

            stmt.setString(1, nomeCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_categoria");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria no banco:");
            e.printStackTrace();
        }

        // CORRIGIDO: nome para nome_categoria
        String sqlInserir = "INSERT INTO categorias (nome_categoria) VALUES (?)";

        try (Connection conn = Database.Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlInserir, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nomeCategoria);
            int lines = stmt.executeUpdate();

            if (lines > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir nova categoria no banco:");
            e.printStackTrace();
        }

        return 0;
    }
}