package br.com.fiap.dao;

import br.com.fiap.bean.Avaliacao;
import br.com.fiap.bean.Pontuacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PontuacaoDAO {
    // Atributo
    private Connection con;

    // Construtor com passagem de parâmetro
    public PontuacaoDAO(Connection con) {
        this.con = con;
    }

    // Getter
    public Connection getCon() {
        return con;
    }

    // Metodo Exclusivo
    public String inserir(Pontuacao pontuacao){
        // Comando sql para inserir dentro da tabela Arquivo
        String sql = "INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao) VALUES (?, ?, ?, ?)";
        // Try with resourses
        // Objeto criado e instanciado dentro do try para fechar automaticamente
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            // Atribuindo valores ao comando INSERT
            ps.setInt(1, pontuacao.getIdPontuacao());
            ps.setFloat(2, pontuacao.getValor());
            ps.setString(3, "Avaliação");
            ps.setDate(4, java.sql.Date.valueOf(pontuacao.getDataPontuacao()));
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
