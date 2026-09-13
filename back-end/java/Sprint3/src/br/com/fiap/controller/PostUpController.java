package br.com.fiap.controller;

import br.com.fiap.model.dao.*;
import br.com.fiap.model.dto.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostUpController {

    // Objetos/Atributos
    private UsuarioDAO usuarioDAO;
    private PostagemDAO postagemDAO;
    private ArquivoDAO arquivoDAO;
    private AvaliacaoDAO avaliacaoDAO;
    private PontuacaoDAO pontuacaoDAO;

    // Construtor
    public PostUpController(Connection con) {
        usuarioDAO = new UsuarioDAO(con);
        postagemDAO = new PostagemDAO(con);
        arquivoDAO = new ArquivoDAO(con);
        avaliacaoDAO = new AvaliacaoDAO(con);
        pontuacaoDAO = new PontuacaoDAO(con);
    }


    //=====================
    // USUARIO
    //=====================

    // cadastrar e inserir
    public String[] cadastrarUsuario() throws SQLException {
        String[] retorno = new String[3];
        Usuario usuario = new Usuario();
        int idUsuario = usuarioDAO.obterProximoId();
        usuario.setIdUsuario(idUsuario);
        retorno[0] = usuario.cadastrarUsuario();
        retorno[1] = usuarioDAO.inserir(usuario);
        retorno[2] = usuarioDAO.exibir(usuario);
        return retorno;
    }

    // Login
    public Usuario realizarLogin(int idUsuario, String email, String senha) throws SQLException {
        return usuarioDAO.buscarUsuario(idUsuario, email, senha);
    }

    // Listar Usuarios
    public ArrayList<Usuario> listarUsuarios(){
        return usuarioDAO.listarCadastrados();
    }

    // Update
    public String alterarUsuario(Usuario usuario){
        return usuarioDAO.alterar(usuario);
    }

    // Select
    public String exibirUsuario(Usuario usuario){
        return usuarioDAO.exibir(usuario);
    }

    // Delete
    public String excluirUsuario(Usuario usuario){
        return usuarioDAO.excluir(usuario);
    }


    //=====================
    // POSTAGEM
    //=====================

    // Criar/Inserir
    public String[] criarPostagem() throws SQLException {
        String[] retorno = new String[3];
        Postagem postagem = new Postagem();
        int idPostagem = postagemDAO.obterProximoId();
        postagem.setIdPostagem(idPostagem);
        retorno[0] = postagem.criarPostagem();
        retorno[1] = postagemDAO.inserir(postagem);
        retorno[2] = postagemDAO.exibir(postagem);
        return retorno;
    }

    // Listar postagens
    public ArrayList<Postagem> listarPostagens(){
        return postagemDAO.listarCadastrados();
    }

    // Update
    public String alterarPostagem(Postagem postagem){
        return postagemDAO.alterar(postagem);
    }

    // Select
    public String exibirPostagem(Postagem postagem){
        return postagemDAO.exibir(postagem);
    }

    // Delete
    public String excluirPostagem(Postagem postagem){
        return postagemDAO.excluir(postagem);
    }


    //=====================
    // ARQUIVO
    //=====================

    // Criar/Inserir
    public String[] inserirArquivo(int idPostagem) throws SQLException {
        String[] retorno = new String[4];
        Arquivo arquivo = new Arquivo();
        arquivo.setIdArquivo(arquivoDAO.obterProximoId());
        int idArquivo = arquivoDAO.obterProximoId();
        retorno[0] = arquivo.importarArquivo();
        retorno[1] = arquivoDAO.inserir(arquivo);
        retorno[2] = postagemDAO.alterarFKArquivo(idPostagem, idArquivo);
        retorno[3] = arquivoDAO.exibir(arquivo);
        return retorno;
    }

    // Acesso câmera
    public String[] cameraArquivo(int idPostagem) throws SQLException {
        String[] retorno = new String[4];
        Arquivo arquivo = new Arquivo();
        arquivo.setIdArquivo(arquivoDAO.obterProximoId());
        int idArquivo = arquivoDAO.obterProximoId();
        retorno[0] = arquivo.acessarCamera();
        retorno[1] = arquivoDAO.inserir(arquivo);
        retorno[2] = postagemDAO.alterarFKArquivo(idPostagem, idArquivo);
        retorno[3] = arquivoDAO.exibir(arquivo);
        return retorno;
    }

    // Listar arquivos
    public ArrayList<Arquivo> listarArquivos(){
        return arquivoDAO.listarCadastrados();
    }

    // Update
    public String alterarArquivo(Arquivo arquivo){
        return arquivoDAO.alterar(arquivo);
    }

    // Delete
    public String excluirArquivo(Arquivo arquivo){
        return arquivoDAO.excluir(arquivo);
    }

    // Select
    public String exibirArquivo(Arquivo arquivo){
        return arquivoDAO.exibir(arquivo);
    }


    //=====================
    // AVALIACAO
    //=====================

    // Criar/Inserir
    public Avaliacao avaliarPostagem(int idPostagem) throws SQLException {
        Postagem postagem = new Postagem();
        postagem.setIdPostagem(idPostagem);
        postagem = postagemDAO.exibirObjeto(postagem);
        int idArquivo = postagemDAO.exibirFKArquivo(idPostagem);
        Arquivo arquivo = new Arquivo();
        arquivo.setIdArquivo(idArquivo);
        arquivo = arquivoDAO.exibirObjeto(arquivo);
        int qtdPostagens = postagemDAO.contarPostagens();
        int idAvaliacao = avaliacaoDAO.obterProximoId();
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.avaliarPostagem(postagem, arquivo, qtdPostagens, idAvaliacao);
        avaliacaoDAO.inserir(avaliacao);
        postagemDAO.alterarFKAvaliacao(idPostagem, idAvaliacao);
        return avaliacao;
    }

    // Select
    public Avaliacao exibirAvaliacao(Avaliacao avaliacao){
        return avaliacaoDAO.exibir(avaliacao);
    }


    //=====================
    // PONTUAÇÃO
    //=====================

    public Pontuacao calcularPontuacao(int idPostagem) throws SQLException {
        int idAvaliacao = postagemDAO.exibirFKAvaliacao(idPostagem);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(idAvaliacao);
        avaliacaoDAO.exibir(avaliacao);
        int idPontuacao = pontuacaoDAO.obterProximoId();
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.calcularPontuacao(avaliacao, idPontuacao);
        pontuacaoDAO.inserir(pontuacao);
        postagemDAO.alterarFKPontuacao(idPostagem, idPontuacao);
        return pontuacao;
    }

    public Pontuacao exibirPontuacao(Pontuacao pontuacao){
        return pontuacaoDAO.exibir(pontuacao);
    }
}
