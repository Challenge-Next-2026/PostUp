-- Consultas SQL

-- 1. Consultar as ações sustentáveis registradas e seu status
SELECT 
    id_postagem AS ID, 
    ds_titulopost AS Título, 
    ds_postagem AS Descrição, 
    dt_postagem AS Data, 
    st_postagem AS Status
FROM POSTAGEM
WHERE st_postagem = 'T'
ORDER BY id_postagem;

-- 2. Consultar Postagens e seus respectivos Autores
SELECT
    u.id_usuario AS ID_User,
    u.nm_usuario AS Usuário,
    p.id_postagem AS ID_Post,
    p.ds_titulopost AS Título,
    pu.qtd_porcentagemparticipacao AS Participação
FROM USUARIO u
INNER JOIN POSTAGEM_USUARIO pu
    ON u.id_usuario = pu.id_usuario
INNER JOIN POSTAGEM p
    ON pu.id_postagem = p.id_postagem
ORDER BY u.num_posicaoranking;

-- 3. Consultar a Avaliação e a Pontuação de cada Postagem
SELECT
    p.id_postagem AS ID_Post,
    p.ds_titulopost AS Título,
    a.ds_criterioavaliacao AS Critério,
    a.vl_notaimpacto AS Impacto,
    a.vl_notadificuldade AS Dificuldade,
    a.vl_notaconfiabilidade AS Confiabilidade,
    a.vl_notafrequencia AS Frequência,
    po.vl_pontuacao AS Pontuação
FROM POSTAGEM p
INNER JOIN AVALIACAO a
    ON p.avaliacao_id_avaliacao = a.id_avaliacao
INNER JOIN PONTUACAO po
    ON p.pontuacao_id_pontuacao = po.id_pontuacao
ORDER BY po.vl_pontuacao DESC;

-- 4. Consultar todas as Postagens e suas Avaliações
-- Até mesmo as Postagens que não tem Avaliação
SELECT
    p.id_postagem AS ID_Post,
    p.ds_titulopost AS Título,
    a.id_avaliacao AS ID_Ava,
    a.ds_criterioavaliacao AS Critério,
    a.vl_notaimpacto AS Impacto
FROM POSTAGEM p
LEFT JOIN AVALIACAO a
    ON p.avaliacao_id_avaliacao = a.id_avaliacao
ORDER BY p.id_postagem;

-- 5. Consultar todas as Postagens
-- mesmo aquelas com campos nulos
SELECT
    p.id_postagem AS ID_Post,
    p.ds_titulopost AS Título,
    a.id_avaliacao AS ID_Ava,
    a.ds_criterioavaliacao AS Critério,
    a.vl_notaimpacto AS Impacto
FROM AVALIACAO a
RIGHT JOIN POSTAGEM p
    ON a.id_avaliacao = p.avaliacao_id_avaliacao
ORDER BY p.id_postagem;

-- 6. Consultar a média de impacto por categoria de cada Avaliação
SELECT
    ds_criterioavaliacao AS Critério,
    COUNT(*) AS qtd_avaliacoes,
    AVG(vl_notaimpacto) AS media_impacto,
    MIN(vl_notaimpacto) AS menor_impacto,
    MAX(vl_notaimpacto) AS maior_impacto
FROM AVALIACAO
GROUP BY ds_criterioavaliacao
ORDER BY media_impacto DESC;

-- 7. Consultar a contagem, soma, média, mínimo e máximo das Pontuações
SELECT
    COUNT(*) AS total_pontuacoes,
    SUM(vl_pontuacao) AS soma_pontos,
    AVG(vl_pontuacao) AS media_pontos,
    MIN(vl_pontuacao) AS menor_pontuacao,
    MAX(vl_pontuacao) AS maior_pontuacao
FROM PONTUACAO;

-- 8. Consultar os Usuários que possuem Postagens 
-- com Pontuação maior ou igual a 60 
SELECT
    u.id_usuario AS ID_User,
    u.nm_usuario AS Nome,
    p.ds_titulopost AS Título,
    po.vl_pontuacao AS Valor
FROM USUARIO u
INNER JOIN POSTAGEM_USUARIO pu
    ON u.id_usuario = pu.id_usuario
INNER JOIN POSTAGEM p
    ON pu.id_postagem = p.id_postagem
INNER JOIN PONTUACAO po
    ON p.pontuacao_id_pontuacao = po.id_pontuacao
WHERE po.vl_pontuacao >= 60
ORDER BY po.vl_pontuacao DESC;

-- 9. Consultar as Validações por tipo
SELECT
    in_tipovalidacao AS Tipo,
    COUNT(*) AS total_validacoes
FROM VALIDACAO
WHERE st_validacao = 'T'
GROUP BY in_tipovalidacao
ORDER BY total_validacoes DESC;

-- 10. Consultar Postagens, seus Arquivos e suas Geolocalizações
-- mesmo que haja Postagens com campos nulos
SELECT
    p.id_postagem AS ID_Post,
    p.ds_titulopost AS Título,
    ar.nm_arquivo AS Arquivo,
    ar.in_tipoarquivo AS Tipo,
    g.ds_latitude AS Latitude,
    g.ds_longitude AS Longitude
FROM POSTAGEM p
LEFT JOIN ARQUIVO ar
    ON p.arquivo_id_arquivo = ar.id_arquivo
LEFT JOIN GEOLOCALIZACAO g
    ON p.geolocalizacao_id_geoloc = g.id_geoloc
ORDER BY p.id_postagem;