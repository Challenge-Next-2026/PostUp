package br.com.fiap.bean;

import java.util.List;

public interface ExibicaoObjetos
{
    public void exibir(List<Usuario> usuarios, int contador, int id, List<Postagem> postagens, int contador2, int idPostagem, List<Arquivo> arquivos, int contador3, List<Avaliacao> avaliacoes, Pontuacao pontuacao);
}
