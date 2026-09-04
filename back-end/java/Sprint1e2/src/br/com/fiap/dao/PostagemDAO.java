package br.com.fiap.dao;

import br.com.fiap.bean.Postagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostagemDAO {
    // Atributo
    private Connection con;

    // Construtor com passagem de parâmetro
    public PostagemDAO(Connection con) {
        this.con = con;
    }

    // Getter
    public Connection getCon() {
        return con;
    }

    // Metodo Exclusivo

    // Metodo para fazer a leitura e definir sempre um id novo
    public int obterProximoId() throws SQLException {
        // Comando SQL que busca o próximo id disponível
        String sql = "SELECT NVL(MAX(id_postagem), 0) + 1 AS proximo_id FROM POSTAGEM";
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

    public String inserir(Postagem postagem){
        // Comando sql para inserir dentro da tabela Postagem
        String sql = "INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, postagem.getIdPostagem());
            ps.setString(2, postagem.getTitulo());
            ps.setString(3, postagem.getDescricao());
            ps.setDate(4, java.sql.Date.valueOf(postagem.getDataPostagem()));
            ps.setString(5, "S");
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            ps.setInt(10, 0);
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
