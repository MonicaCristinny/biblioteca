package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/* Conectando banco ao sistema */

public class Database {
    public class Conexao {

        private static final String URL =
                "jdbc:mysql://localhost:3306/biblioteca";

        private static final String USER = "root";
        private static final String PASSWORD = "123456";

        public static Connection conectar() {

            try {
                System.out.println("Conectado");
                return DriverManager.getConnection(URL, USER, PASSWORD);

            } catch (SQLException e) {
                System.out.println("Erro ao conectar");
                e.printStackTrace();
                return null;
            }
        }
    }

}
