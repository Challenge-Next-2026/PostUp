-- Carga inicial de dados

-- INSERT NA TABELA USUARIO
-- 10 USUARIOS NO TOTAL
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (1, 'Giovanni Zorzetto', 'gigiozetto@gmail.com', 'qwert', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 2);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (2, 'Felipe Lima', 'felipelimaoliveira2008@gmai.com', 'senha123', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 7);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (3, 'Raphael Gomes', 'raphael.rgb07@gmail.com', 'raphael123', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 3);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (4, 'Gustavo Molina', 'gustavo.molina@gmail.com', 'profDados', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 8);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (5, 'Ana Clara', 'ana.clara@gmail.com', 'Ana@1234', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 10);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (6, 'Sofia Borges', 'sofia.borges@gmail.com', 'sofia_321', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 6);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (7, 'Francisco Halisson', 'francisco.halisson@gmail.com', 'halisson@321', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 4);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (8, 'Kevin Rodrigues', 'kevin.rodrigues@gmail.com', 'kevin_951', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 9);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (9, 'Julia Silva', 'julia.silva@gmail.com', 'juliaSilva753', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 1);
INSERT INTO USUARIO(id_usuario, nm_usuario, ds_email, ds_senha, dt_cadastro, st_conta, num_posicaoranking) 
VALUES (10, 'Beatriz Lopes', 'bia.lopes@gmail.com', 'BiaLopes_10', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 5);

-- INSERT NA TABELA ARQUIVO
-- 10 ARQUIVOS NO TOTAL
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (1, 'ft-plantas', 'img', 200, 'https://ft-plantas.img', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (2, 'vd-plantas', 'mp4', 450, 'https://vd-plantas.mp4', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (3, 'ft-reciclagem', 'png', 125, 'https://ft-reciclagem.png', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (4, 'audio-transporte', 'mp3', 50, 'https://audio-trasporte.mp3', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (5, 'ft-bike', 'jpeg', 734, 'https://ft-bike.jpeg', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (6, 'arquivo-km', 'pdf', 211, 'https://arquivo-km.pdf', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (7, 'ft-conta-energia', 'img', 820, 'https://ft-conta-energia.img', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (8, 'vd-solar', 'mp4', 1000, 'https://vd-solar.mp4', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (9, 'ft-lixo', 'jpeg', 78, 'https://ft-lixo.jpeg', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO ARQUIVO(id_arquivo, nm_arquivo, in_tipoarquivo, ds_tamanho, cod_url, dt_upload)
VALUES (10, 'arquivo-conta-agua', 'pdf', 390, 'https://arquivo-conta-agua.pdf', TO_DATE('28/10/2026', 'DD/MM/YYYY'));

-- INSERT NA TABELA GEOLOCALIZACAO
-- 10 GEOLOCALIZAÇÕES NO TOTAL
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (1, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (2, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (3, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (4, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (5, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (6, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (7, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (8, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (9, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO GEOLOCALIZACAO(id_geoloc, ds_latitude, ds_longitude, dt_registro)
VALUES (10, 33, 73, TO_DATE('28/10/2026', 'DD/MM/YYYY'));

-- INSERT NA TABELA VALIDACAO
-- 10 VALIDAÇÕES NO TOTAL
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (1, 'IA', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (2, 'MANUAL', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (3, 'MANUAL', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (4, 'IA', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (5, 'IA', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (6, 'IA', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (7, 'MANUAL', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (8, 'IA', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (9, 'MANUAL', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO VALIDACAO(id_validacao, in_tipovalidacao, st_validacao, dt_validacao)
VALUES (10, 'IA', 'T', TO_DATE('28/10/2026', 'DD/MM/YYYY'));

-- INSERT NA TABELA AVALIACAO
-- 10 AVALIAÇÕES NO TOTAL
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (1, 'Reflorestamento', 94, 45, 99, 25, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (2, 'Agricultura Sustentável', 84, 42, 50, 50, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (3, 'Reciclagem', 65, 85, 75, 99, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (4, 'Mobilidade Sustentável', 88, 68, 25, 75, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (5, 'Mobilidade Sustentável', 88, 68, 1, 99, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (6, 'Reutilização', 72, 75, 50, 25, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (7, 'Economia de Energia', 72, 90, 75, 25, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (8, 'Energia Renovável', 92, 30, 25, 99, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (9, 'Redução de Resíduos', 86, 88, 99, 50, TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO AVALIACAO(id_avaliacao, ds_criterioavaliacao, vl_notaimpacto, vl_notadificuldade, vl_notaconfiabilidade, vl_notafrequencia, dt_avaliacao)
VALUES (10, 'Economia de Água', 68, 88, 50, 75, TO_DATE('28/10/2026', 'DD/MM/YYYY'));

-- INSERT NA TABELA PONTUACAO
-- 10 PONTUAÇÕES NO TOTAL
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (1, 78.8, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (2, 62, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (3, 75.4, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (4, 63.8, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (5, 59, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (6, 61.3, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (7, 71.8, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (8, 60.2, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (9, 86.7, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));
INSERT INTO PONTUACAO(id_pontuacao, vl_pontuacao, ds_origempontuacao, dt_pontuacao)
VALUES (10, 67.3, 'AVALIAÇÃO', TO_DATE('28/10/2026', 'DD/MM/YYYY'));

-- INSERT NA TABELA POSTAGEM
-- 12 POSTAGENS NO TOTAL
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (1, 'Plantação de mudas', 'Plantei três mudas de espécies nativas em uma área preparada para recuperação ambiental.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 1, 1, 1, 1, 1);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (2, 'Horta Sustentável', 'Criei uma pequena horta utilizando práticas de cultivo sustentável e aproveitei restos orgânicos para produzir composto.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 2, 2, 2, 2, 2);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (3, 'Reciclagem Materiais', 'Hoje separei 5 kg de materiais recicláveis e levei tudo para o ponto de coleta do meu bairro.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 3, 3, 3, 3, 3);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (4, 'Transporte Público', 'Utilizei transporte público durante cinco dias para reduzir o uso do carro nos deslocamentos.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 4, 4, 4, 4, 4);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (5, 'Bike Escola', 'Durante esta semana fui de bicicleta para a faculdade em vez de utilizar o carro. Percorri aproximadamente 35 km no total.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 5, 5, 5, 5, 5);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (6, 'Reutilização', 'Reutilizei potes de vidro que eu tinha em casa para armazenar alimentos, evitando comprar novos recipientes.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 6, 6, 6, 6, 6);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (7, 'Troca de Lâmpadas', 'Troquei cinco lâmpadas antigas por lâmpadas LED para reduzir o consumo de energia.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 7, 7, 7, 7, 7);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (8, 'Painéis Solares', 'Instalei painéis solares na minha residência para gerar parte da energia que consumo.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 8, 8, 8, 8, 8);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (9, 'Gerenciamento Sacola', 'Passei a levar uma sacola reutilizável nas compras para evitar sacolas descartáveis.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 9, 9, 9, 9, 9);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (10, 'Tempo de Banho', 'Reduzi o tempo de banho e acompanhei a mudança no consumo de água por meio do hidrômetro.', TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', 10, 10, 10, 10, 10);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (11, 'Atividade Voluntária', 'Participei de uma atividade voluntária para ajudar na preservação de uma área verde próxima à comunidade.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', null, null, null, null, null);
INSERT INTO POSTAGEM(id_postagem, ds_titulopost, ds_postagem, dt_postagem, st_postagem, 
                     geolocalizacao_id_geoloc, arquivo_id_arquivo, validacao_id_validacao, avaliacao_id_avaliacao, pontuacao_id_pontuacao)
VALUES (12, 'Consumo Consciente', 'Antes de comprar uma roupa nova, procurei uma peça usada em bom estado e encontrei uma em um brechó.', 
        TO_DATE('28/10/2026', 'DD/MM/YYYY'), 'T', null, null, null, null, null);
        
-- INSERT NA TABELA POSTAGEM USUARIO
-- 10 REGISTROS NO TOTAL
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (1, 1, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (2, 2, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (3, 3, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (4, 4, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (5, 5, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (6, 6, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (7, 7, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (8, 8, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (9, 9, 99);
INSERT INTO POSTAGEM_USUARIO(id_usuario, id_postagem, qtd_porcentagemparticipacao) VALUES (10, 10, 99);

