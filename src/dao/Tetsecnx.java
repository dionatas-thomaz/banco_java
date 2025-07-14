package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class Tetsecnx {
    public static void main(String[] args) {
        try (Connection conn = ConexaoDAO.getConnection()) {
            if (conn != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao estabelecer conexão: conexão nula.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
