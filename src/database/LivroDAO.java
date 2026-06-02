package database;

import model.Livros;
import database.Database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public boolean salvar(Livros livro) {
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

    public List<Livros> listarTodos() {
        List<Livros> livrosLista = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livros livro = new Livros(
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getInt("id_categoria"),
                        rs.getInt("id_autor"),
                        rs.getString("status")
                );
                livrosLista.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros.");
            e.printStackTrace();
        }
        return livrosLista;
    }

    // Busca filtrada retornando List<Livros>
    public List<Livros> buscarPorNome(String nomeBusca) {
        List<Livros> livrosLista = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomeBusca + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // CORREÇÃO DA LINHA 74: Chamando o construtor correto 'Livros'
                    Livros livro = new Livros(
                            rs.getInt("id_livro"),
                            rs.getString("titulo"),
                            rs.getInt("id_categoria"),
                            rs.getInt("id_autor"),
                            rs.getString("status")
                    );
                    livrosLista.add(livro);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por nome.");
            e.printStackTrace();
        }
        return livrosLista;
    }

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