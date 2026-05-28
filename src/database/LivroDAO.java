package database;

import model.Livro;
import database.Database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    // parte de "salvar"
    public boolean salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, id_categoria, id_autor) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getId_categoria());
            stmt.setInt(3, livro.getId_autor());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar livro no banco.");
            e.printStackTrace();
            return false;
        }
    }

    // parte de "lista" livros
    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getInt("id_categoria"),
                        rs.getInt("id_autor")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros.");
            e.printStackTrace();
        }
        return livros;
    }

    // parte de "filtrar" pelo nome
    public List<Livro> buscarPorNome(String nomeBusca) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // O "%" faz o MySQL buscar qualquer livro que contenha aquela palavra no título
            stmt.setString(1, "%" + nomeBusca + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro(
                            rs.getInt("id_livro"),
                            rs.getString("titulo"),
                            rs.getInt("id_categoria"),
                            rs.getInt("id_autor")
                    );
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por nome.");
            e.printStackTrace();
        }
        return livros;
    }

    // parte de "excluir"
    public boolean excluir(int idLivro) {
        String sql = "DELETE FROM livro WHERE id_livro = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivro);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar livro.");
            e.printStackTrace();
            return false;
        }
    }
}