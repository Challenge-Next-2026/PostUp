package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Objeto de conexão
    private static Connection connection;

    // Fechar conexão
    public static void closeConnection(){
        // Tratamento de erros
        try {
            // Se a conexão não estiver fechada, ela fecha agora.
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.printf("Erro: " + e.getMessage());
        }
    }

    // Abrir conexão / getConnection
    public static Connection getConnection() {
        // Tratamento de erros
        try {
            // Se a conexão não é nula
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Busca dados de conexão nas variáveis de ambiente
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");

            if (url == null || user == null || pass == null) {
                throw new RuntimeException("Variáveis de Ambiente do banco não configuradas!");
            }
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Erro nome da classe: " + e.getMessage());
        }
        return connection;
    }
}
