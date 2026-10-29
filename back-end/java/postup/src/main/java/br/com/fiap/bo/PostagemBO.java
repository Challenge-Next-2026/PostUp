package br.com.fiap.bo;

import br.com.fiap.dao.PostagemDAO;
import br.com.fiap.to.PostagemTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PostagemBO {
    // Atributo
    private PostagemDAO postagemDAO;

    // obterProximoId()
    public int obterProximoId() throws SQLException {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.obterProximoId();
    }

    // save()
    public PostagemTO save(PostagemTO postagem){
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.save(postagem);
    }

    // update()
    public PostagemTO update(PostagemTO postagem) {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.update(postagem);
    }

    // findAll()
    public ArrayList<PostagemTO> findAll() {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.findAll();
    }

    // findById()
    public PostagemTO findById(int idPostagem) {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.findById(idPostagem);
    }

    // delete()
    public boolean delete(int idPostagem) {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.delete(idPostagem);
    }

    // savePostWithUser()
    public PostagemTO savePostWithUser(PostagemTO postagem, int idUsuario) throws SQLException{
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.savePostWithUser(postagem, idUsuario);
    }

    // updateByOwner()
    public boolean updateByOwner(PostagemTO postagem, int idUsuario) throws SQLException {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.updateByOwner(postagem, idUsuario);
    }

    // deleteByOwner()
    public boolean deleteByOwner(int idPostagem, int idUsuario) throws SQLException {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.deleteByOwner(idPostagem, idUsuario);
    }

    // countPosts()
    public int countPosts(int idUsuario) {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.countPosts(idUsuario);
    }

    // exibirFKArquivo()
    public int exibirFKArquivo(int idPostagem) {
        postagemDAO = new PostagemDAO();
        // Regras de negócio
        return postagemDAO.exibirFKArquivo(idPostagem);
    }
}
