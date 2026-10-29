package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO{
    // Atributo
    private UsuarioDAO usuarioDAO;

    // obterProximoId()
    public int obterProximoId() throws SQLException {
        usuarioDAO = new UsuarioDAO();
        // Regras de negócio
        return usuarioDAO.obterProximoId();
    }

    // save()
    public UsuarioTO save(UsuarioTO usuario) throws SQLException{
        usuarioDAO = new UsuarioDAO();
        // Remove possíveis espaços na digitação do email
        usuario.setEmail(usuario.getEmail().trim());
        // Verifica se o email já está cadastrado
        if (usuarioDAO.findEmail(usuario.getEmail())) {
            return null;
        }
        // Atribui valor ao ID e a data
        usuario.setIdUsuario(usuarioDAO.obterProximoId());
        usuario.setDataCadastro(java.time.LocalDate.now());
        // Retorno
        return usuarioDAO.save(usuario);
    }

    // findAll()
    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        // Regras de negócio
        return usuarioDAO.findAll();
    }

    // findById()
    public UsuarioTO findById(int idUsuario) {
        usuarioDAO = new UsuarioDAO();
        // Regras de negócio
        return usuarioDAO.findById(idUsuario);
    }

    // delete()
    public boolean delete(int idUsuario) {
        usuarioDAO = new UsuarioDAO();
        // Regras de negócio
        return usuarioDAO.delete(idUsuario);
    }

    // login()
    public UsuarioTO login(String email, String senha) throws SQLException{
        usuarioDAO = new UsuarioDAO();
        // Regras de negócio
        return usuarioDAO.login(email, senha);
    }

    // updateByOwner()
    public UsuarioTO update(UsuarioTO usuario, int idUsuarioLogado) throws SQLException {
        usuarioDAO = new UsuarioDAO();
        // Regras de negócio
        return usuarioDAO.update(usuario, idUsuarioLogado);
    }
}
