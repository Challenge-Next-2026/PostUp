package br.com.fiap.bo;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.to.AvaliacaoTO;
import br.com.fiap.bo.PostagemBO;
import br.com.fiap.to.PostagemTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AvaliacaoBO {
    // Atributo
    private AvaliacaoDAO avaliacaoDAO;
    private PostagemBO postagemBO;

    // obterProximoId()
    public int obterProximoId() throws SQLException {
        avaliacaoDAO = new AvaliacaoDAO();
        // Regras de negócio
        return avaliacaoDAO.obterProximoId();
    }

    // save()
    public AvaliacaoTO save(AvaliacaoTO avaliacao){
        avaliacaoDAO = new AvaliacaoDAO();
        // Regras de negócio
        return avaliacaoDAO.save(avaliacao);
    }

    // update()
    public AvaliacaoTO update(AvaliacaoTO avaliacao) {
        avaliacaoDAO = new AvaliacaoDAO();
        // Regras de negócio
        return avaliacaoDAO.update(avaliacao);
    }

    // findAll()
    public ArrayList<AvaliacaoTO> findAll() {
        avaliacaoDAO = new AvaliacaoDAO();
        // Regras de negócio
        return avaliacaoDAO.findAll();
    }

    // findById()
    public AvaliacaoTO findById(int idAvaliacao) {
        avaliacaoDAO = new AvaliacaoDAO();
        // Regras de negócio
        return avaliacaoDAO.findById(idAvaliacao);
    }

    // delete()
    public boolean delete(int idAvaliacao) {
        avaliacaoDAO = new AvaliacaoDAO();
        // Regras de negócio
        return avaliacaoDAO.delete(idAvaliacao);
    }

    // saveByOwner()
    public AvaliacaoTO saveByOwner(AvaliacaoTO avaliacao, int idPostagem, int idUsuario) throws SQLException {
        avaliacaoDAO = new AvaliacaoDAO();
        // Regras de negócio
        return avaliacaoDAO.saveByOwner(avaliacao, idPostagem, idUsuario);
    }

    // Metodo para classificar a categoria de uma postagem específica
    private String identificarCategoria(String titulo, String descricao) {
        // Armazenando titulo e descrição em uma só string
        String texto = (titulo + " " + descricao).toLowerCase();
        // Validação para retornar Plantio
        if (texto.contains("plant") || texto.contains("árvore") || texto.contains("arvore")) {
            return "Plantio";
        }
        // Validação para retornar Reciclagem
        if (texto.contains("reciclagem") || texto.contains("reciclar") || texto.contains("reutilizar")) {
            return "Reciclagem";
        }
        // Validação para retornar Transporte Sustentável
        if (texto.contains("bicicleta") || texto.contains("transporte público") || texto.contains("transporte publico") || texto.contains("caminhar")) {
            return "Transporte sustentável";
        }
        // Validação para retornar Economia de recursos
        if (texto.contains("água") || texto.contains("agua") || texto.contains("energia") || texto.contains("eletricidade")) {
            return "Economia de recursos";
        }

        return "Outras ações";
    }

    // Metodo para calcular o impacto e a dificuldade com base na categoria definida
    private void calcularImpactoDificuldade(AvaliacaoTO avaliacao, String categoria) {
        // Estrutura condicional avaliando categoria
        switch (categoria) {
            // Case plantio
            case "Plantio":
                avaliacao.setNotaImpacto(90);
                avaliacao.setNotaDificuldade(60);
                avaliacao.setCriterio("Plantio");
                break;
            // Case reciclagem
            case "Reciclagem":
                avaliacao.setNotaImpacto(80);
                avaliacao.setNotaDificuldade(40);
                avaliacao.setCriterio("Reciclagem");
                break;
            // Case transporte sustentavel
            case "Transporte sustentável":
                avaliacao.setNotaImpacto(85);
                avaliacao.setNotaDificuldade(60);
                avaliacao.setCriterio("Transporte sustentável");
                break;
            // Case economia de recursos
            case "Economia de recursos":
                avaliacao.setNotaImpacto(80);
                avaliacao.setNotaDificuldade(35);
                avaliacao.setCriterio("Economia de recursos");
                break;
            // Case Outras categorias
            default:
                avaliacao.setNotaImpacto(50);
                avaliacao.setNotaDificuldade(50);
                avaliacao.setCriterio("Outras ações");
                break;
        }
    }

    // Metodo que calcula a frequencia com base na quantidade de posts por usuário
    private int calcularFrequencia(int quantidadePostagens) {
        // Se for 0 ou menor
        if (quantidadePostagens <= 0) {
            return 0;
        }
        // Se for 1
        if (quantidadePostagens == 1) {
            return 25;
        }
        // Se for 2
        if (quantidadePostagens == 2) {
            return 50;
        }
        // Se for 3
        if (quantidadePostagens == 3) {
            return 75;
        }
        // Se for qualquer número acima de 4
        return 100;
    }

    // Metodo que calcula a confiabilidade com base na integridade do arquivo
    private int calcularConfiabilidade(String titulo, String descricao, boolean arquivoValido) {
        // Nota padrão
        int nota = 50;
        // Se tiver título e descrição
        if (titulo != null && !titulo.isBlank() && descricao != null && !descricao.isBlank()) {
            nota = 70;
        }
        // Se for válido
        if (arquivoValido) {
            nota += 30;
        }
        // Retorna o menor valor
        return Math.min(nota, 100);
    }

    // Metodo que faz a avaliação completa
    public AvaliacaoTO avaliarPostagem(int idPostagem, int idUsuario) throws SQLException {
        // Buscar a postagem
        postagemBO = new PostagemBO();
        PostagemTO postagem = postagemBO.findById(idPostagem);
        // Se a postagem for nula
        if (postagem == null) {
            return null;
        }

        // Criar a avaliação
        AvaliacaoTO avaliacao = new AvaliacaoTO();

        // Identificar categoria pelo título e descrição
        String categoria = identificarCategoria(postagem.getTitulo(), postagem.getDescricao());

        // Calcular impacto e dificuldade
        calcularImpactoDificuldade(avaliacao, categoria);

        // Calcular frequência do usuário
        int quantidadePostagens = postagemBO.countPosts(idUsuario);
        avaliacao.setNotaFrequencia(calcularFrequencia(quantidadePostagens));

        // Verificar arquivo associado
        int idArquivo = postagemBO.exibirFKArquivo(idPostagem);
        boolean arquivoValido = idArquivo > 0;

        // Calcular confiabilidade
        avaliacao.setNotaConfiabilidade(calcularConfiabilidade(postagem.getTitulo(), postagem.getDescricao(), arquivoValido));

        // Definir data da avaliação
        avaliacao.setDataAvaliacao(java.time.LocalDate.now());

        // Associar a avaliação à postagem
        return saveByOwner(avaliacao, idPostagem, idUsuario);
    }
}
