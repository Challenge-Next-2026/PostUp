package br.com.fiap.dao;

import br.com.fiap.bean.Arquivo;
import br.com.fiap.bean.Postagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

    // Metodo Exclusivo
    public String inserir(List<Arquivo> arquivos, int idArquivo){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload) VALUES (?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, arquivos.get(idArquivo - 1).getIdArquivo());
            ps.setString(2, arquivos.get(idArquivo - 1).getNomeArquivo());
            ps.setString(3, arquivos.get(idArquivo - 1).getTipo());
            ps.setString(3, arquivos.get(idArquivo - 1).getTamanho());
            ps.setString(3, arquivos.get(idArquivo - 1).getUrl());
            ps.setDate(5, java.sql.Date.valueOf(arquivos.get(idArquivo - 1).getDataUpload()));
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
}
