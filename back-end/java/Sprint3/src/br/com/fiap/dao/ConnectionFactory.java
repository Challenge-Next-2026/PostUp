package br.com.fiap.dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Metodo abrirConexao()
    public static Connection abrirConexao(){
        // Criação de objeto
        Connection con = null;
        try {
            // import do DRIVER
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 1ª parte: Ferramenta java para conexão com banco de dados - 2ª parte: SGBD usado - 3ª parte: Modelo do SGBD - 4ª parte: domínio de conexão - 5ª parte: PORT(Porta) - 6ª parte: SID.
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            // Definição de constantes próprias
            final String USER = "rm569464";
            final String PASS = "110507";
            // Instanciação do objeto con
            con = DriverManager.getConnection(url, USER, PASS);
            JOptionPane.showMessageDialog(null, "Conexão Aberta", "Atenção", JOptionPane.WARNING_MESSAGE);
        // Erro de importação da classe driver
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: A classe de conexão não foi encontrada!\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        // Erro de conexão com SQL
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        // Erro genérico
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

    // Metodo fecharConexao()
    public static void fecharConexao(Connection con){
        try {
            // Fechamento do objeto de conexão
            con.close();
            JOptionPane.showMessageDialog(null, "Conexão Fechada.", "Atenção", JOptionPane.WARNING_MESSAGE);
        // Erro de conexão com SQL
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        // Erro genérico
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
