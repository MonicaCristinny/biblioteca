package database;

import database.Database.Conexao;
import model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categorias> listarTodas(){
        List<Categorias> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Categorias categoria = new Categorias(
                        rs.getInt("id_categoria"),
                        rs.getString("nome")
                );
                categorias.add(categoria);
            }
        } catch (SQLException e){
            System.out.println("Erro ao listar categorias do banco local.");
            e.printStackTrace();
        }
        return categorias;
    }
}
