package br.com.fiap.bo;

import br.com.fiap.dao.PontuacaoDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.AvaliacaoTO;
import br.com.fiap.to.PontuacaoTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PontuacaoBO {
    // Atributo
    private PontuacaoDAO pontuacaoDAO;

    // obterProximoId()
    public int obterProximoId() throws SQLException {
        pontuacaoDAO = new PontuacaoDAO();
        // Regras de negócio
        return pontuacaoDAO.obterProximoId();
    }

    // save()
    public PontuacaoTO save(PontuacaoTO pontuacao){
        pontuacaoDAO = new PontuacaoDAO();
        // Regras de negócio
        return pontuacaoDAO.save(pontuacao);
    }

    // update()
    public PontuacaoTO update(PontuacaoTO pontuacao) {
        pontuacaoDAO = new PontuacaoDAO();
        // Regras de negócio
        return pontuacaoDAO.update(pontuacao);
    }

    // findAll()
    public ArrayList<PontuacaoTO> findAll() {
        pontuacaoDAO = new PontuacaoDAO();
        // Regras de negócio
        return pontuacaoDAO.findAll();
    }

    // findById()
    public PontuacaoTO findById(int idPontuacao) {
        pontuacaoDAO = new PontuacaoDAO();
        // Regras de negócio
        return pontuacaoDAO.findById(idPontuacao);
    }

    // delete()
    public boolean delete(int idPontuacao) {
        pontuacaoDAO = new PontuacaoDAO();
        // Regras de negócio
        return pontuacaoDAO.delete(idPontuacao);
    }

    // calcularPontuacao()
    public PontuacaoTO calcularPontuacao(AvaliacaoTO avaliacao) {
        // Armazenando valores do objeto avaliação
        float impacto = avaliacao.getNotaImpacto();
        float dificuldade = avaliacao.getNotaDificuldade();
        float confiabilidade = avaliacao.getNotaConfiabilidade();
        float frequencia = avaliacao.getNotaFrequencia();
        // Calculando valor com peso
        float valor = (impacto * 0.40f) + (dificuldade * 0.30f) + (confiabilidade * 0.20f) + (frequencia * 0.10f);
        // Populando objeto pontuação
        PontuacaoTO pontuacao = new PontuacaoTO();
        pontuacao.setValor(Math.round(valor * 100.0f) / 100.0f); // 2 casas decimáis
        pontuacao.setDataPontuacao(java.time.LocalDate.now());
        // Retornando objeto
        return pontuacao;
    }
}
