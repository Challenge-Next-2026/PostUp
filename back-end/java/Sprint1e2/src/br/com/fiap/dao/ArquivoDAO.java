package br.com.fiap.dao;

import br.com.fiap.dto.Arquivo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArquivoDAO {
    // Atributo
    private Connection con;

    // Construtor com passagem de parâmetro
    public ArquivoDAO(Connection con) {
        this.con = con;
    }

    // Getter
    public Connection getCon() {
        return con;
    }

    // Metodos Exclusivo
    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_arquivo), 0) + 1 AS proximo_id FROM ARQUIVO";
        // try-with-resourses passando o comando sql para o objeto ps e armazenando a execução em um objeto rs
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Condicional que avalia se a próxima linha está preenchida
            if (rs.next()) {
                // retorna o próximo id
                return rs.getInt("proximo_id");
            }
        }
        throw new SQLException("Não foi possível obter o próximo ID.");
    }

    // INSERT
    public String inserir(Arquivo arquivo){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload) VALUES (?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, arquivo.getIdArquivo());
            ps.setString(2, arquivo.getNomeArquivo());
            ps.setString(3, arquivo.getTipo());
            ps.setString(4, arquivo.getTamanho());
            ps.setString(5, arquivo.getUrl());
            ps.setDate(6, java.sql.Date.valueOf(arquivo.getDataUpload()));
            // Verificação
            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso!";
            } else {
                return "Erro ao Inserir!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    // SELECT
    public Arquivo exibir(Arquivo arquivo, int idArquivo){
        // Comando SQL
        String sql = "SELECT id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload FROM ARQUIVO WHERE id_arquivo = ?";
        // try-with-resources
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Substituição de ?
            ps.setInt(1, idArquivo);
            try (ResultSet rs = ps.executeQuery()){
                // Validação se há postagens
                if (rs.next()) {
                    arquivo.setIdArquivo(rs.getInt("id_arquivo"));
                    arquivo.setNomeArquivo(rs.getString("nm_arquivo"));
                    arquivo.setTipo(rs.getString("in_tipoarquivo"));
                    arquivo.setTamanho(rs.getString("ds_tamanho"));
                    arquivo.setUrl(rs.getString("cod_url"));
                    arquivo.setDataUpload(rs.getDate("dt_upload").toLocalDate());
                    return arquivo;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
