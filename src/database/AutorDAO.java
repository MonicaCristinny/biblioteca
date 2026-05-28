package database;

import model.Autores;

import database.Database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    //busca o nome do autores
    public List<Autores> listarTodos(){
        List<Autores> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor ORDEM BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
            Autores autor = new Autores(
                    rs.getInt("id_autor"),
                    rs.getString("nome")
            );
            autores.add(autor);
            }
        } catch (SQLException e){
            System.out.println("Erro ao listar autores do banco local.");
            e.printStackTrace();
        }
        return autores;
    }
}
