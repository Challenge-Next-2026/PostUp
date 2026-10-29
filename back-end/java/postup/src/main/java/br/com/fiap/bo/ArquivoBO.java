package br.com.fiap.bo;

import br.com.fiap.dao.ArquivoDAO;
import br.com.fiap.to.ArquivoTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArquivoBO {
    // Atributo
    private ArquivoDAO arquivoDAO;

    // obterProximoId()
    public int obterProximoId() throws SQLException {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.obterProximoId();
    }

    // save()
    public ArquivoTO save(ArquivoTO arquivo){
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.save(arquivo);
    }

    // update()
    public ArquivoTO update(ArquivoTO arquivo) {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.update(arquivo);
    }

    // findAll()
    public ArrayList<ArquivoTO> findAll() {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.findAll();
    }

    // findById()
    public ArquivoTO findById(int idArquivo) {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.findById(idArquivo);
    }

    // delete()
    public boolean delete(int idArquivo) {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.delete(idArquivo);
    }

    // saveByOwner()
    public ArquivoTO saveByOwner(ArquivoTO arquivo, int idUsuario) throws SQLException {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.saveByOwner(arquivo, idUsuario);
    }

    // updateByOwner()
    public ArquivoTO updateByOwner(ArquivoTO arquivo, int idUsuario) throws SQLException {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.updateByOwner(arquivo, idUsuario);
    }

    // deleteByOwner()
    public boolean deleteByOwner(int idArquivo, int idUsuario) throws SQLException {
        arquivoDAO = new ArquivoDAO();
        // Regras de negócio
        return arquivoDAO.deleteByOwner(idArquivo, idUsuario);
    }
}
