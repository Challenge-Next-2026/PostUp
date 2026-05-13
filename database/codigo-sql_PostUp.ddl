-- Gerado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   em:        2026-05-13 18:24:45 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE ARQUIVO 
    ( 
     id_arquivo     NUMBER  NOT NULL , 
     nm_arquivo     VARCHAR2 (50 CHAR)  NOT NULL , 
     in_tipoArquivo VARCHAR2 (10 CHAR)  NOT NULL , 
     ds_tamanho     VARCHAR2 (100 CHAR)  NOT NULL , 
     cod_URL        VARCHAR2 (300 CHAR)  NOT NULL , 
     dt_upload      DATE  NOT NULL 
    ) 
;

ALTER TABLE ARQUIVO 
    ADD CONSTRAINT ARQUIVO_PK PRIMARY KEY ( id_arquivo ) ;

CREATE TABLE AVALIACAO 
    ( 
     id_avaliacao          NUMBER  NOT NULL , 
     ds_criterioAvaliacao  VARCHAR2 (20 CHAR)  NOT NULL , 
     vl_notaImpacto        NUMBER (100)  NOT NULL , 
     vl_notaDificuldade    NUMBER (100)  NOT NULL , 
     vl_notaConfiabilidade NUMBER (100)  NOT NULL , 
     vl_notaFrequencia     NUMBER (100)  NOT NULL , 
     dt_avaliacao          DATE  NOT NULL 
    ) 
;

ALTER TABLE AVALIACAO 
    ADD CONSTRAINT AVALIACAO_PK PRIMARY KEY ( id_avaliacao ) ;

CREATE TABLE GEOLOCALIZACAO 
    ( 
     id_geolocalizacao NUMBER  NOT NULL , 
     ds_latitude       VARCHAR2 (10 CHAR)  NOT NULL , 
     ds_longitude      VARCHAR2 (10 CHAR)  NOT NULL , 
     dt_registro       DATE  NOT NULL 
    ) 
;

ALTER TABLE GEOLOCALIZACAO 
    ADD CONSTRAINT GEOLOCALIZACAO_PK PRIMARY KEY ( id_geolocalizacao ) ;

CREATE TABLE PONTUACAO 
    ( 
     id_pontuacao       NUMBER  NOT NULL , 
     vl_pontuacao       NUMBER (100)  NOT NULL , 
     ds_origemPontuacao VARCHAR2 (30 CHAR)  NOT NULL , 
     dt_pontuacao       DATE  NOT NULL 
    ) 
;

ALTER TABLE PONTUACAO 
    ADD CONSTRAINT PONTUACAO_PK PRIMARY KEY ( id_pontuacao ) ;

CREATE TABLE POSTAGEM 
    ( 
     id_postagem                      NUMBER  NOT NULL , 
     ds_tituloPost                    VARCHAR2 (20 CHAR)  NOT NULL , 
     ds_postagem                      VARCHAR2 (200 CHAR)  NOT NULL , 
     dt_postagem                      DATE  NOT NULL , 
     st_postagem                      CHAR (1)  NOT NULL , 
--  ERROR: Column name length exceeds maximum allowed length(30) 
     GEOLOCALIZACAO_id_geolocalizacao NUMBER  NOT NULL , 
     ARQUIVO_id_arquivo               NUMBER  NOT NULL , 
     VALIDACAO_id_validacao           NUMBER  NOT NULL , 
     AVALIACAO_id_avaliacao           NUMBER  NOT NULL , 
     PONTUACAO_id_pontuacao           NUMBER  NOT NULL 
    ) 
;

ALTER TABLE POSTAGEM 
    ADD CONSTRAINT POSTAGEM_PK PRIMARY KEY ( id_postagem, AVALIACAO_id_avaliacao ) ;

CREATE TABLE POSTAGEM_USUARIO 
    ( 
     id_usuario                  NUMBER  NOT NULL , 
     id_postagem                 NUMBER  NOT NULL , 
     qtd_porcentagemParticipacao FLOAT  NOT NULL , 
     USUARIO_id_usuario          NUMBER  NOT NULL , 
     POSTAGEM_id_postagem        NUMBER  NOT NULL , 
     POSTAGEM_id_avaliacao       NUMBER  NOT NULL 
    ) 
;

ALTER TABLE POSTAGEM_USUARIO 
    ADD CONSTRAINT POSTAGEM_USUARIO_PK PRIMARY KEY ( id_usuario, id_postagem ) ;

CREATE TABLE USUARIO 
    ( 
     id_usuario         NUMBER  NOT NULL , 
     nm_usuario         VARCHAR2 (100 CHAR)  NOT NULL , 
     ds_email           VARCHAR2 (50 CHAR)  NOT NULL , 
     ds_senha           VARCHAR2 (50 CHAR)  NOT NULL , 
     dt_cadastro        DATE  NOT NULL , 
     st_conta           CHAR (1)  NOT NULL , 
     num_posicaoRanking NUMBER  NOT NULL 
    ) 
;

ALTER TABLE USUARIO 
    ADD CONSTRAINT USUARIO_PK PRIMARY KEY ( id_usuario ) ;

CREATE TABLE VALIDACAO 
    ( 
     id_validacao     NUMBER  NOT NULL , 
     in_tipoValidacao VARCHAR2 (50 CHAR)  NOT NULL , 
     st_validacao     CHAR (1)  NOT NULL , 
     dt_validacao     DATE  NOT NULL 
    ) 
;

ALTER TABLE VALIDACAO 
    ADD CONSTRAINT VALIDACAO_PK PRIMARY KEY ( id_validacao ) ;

ALTER TABLE POSTAGEM 
    ADD CONSTRAINT POSTAGEM_ARQUIVO_FK FOREIGN KEY 
    ( 
     ARQUIVO_id_arquivo
    ) 
    REFERENCES ARQUIVO 
    ( 
     id_arquivo
    ) 
;

ALTER TABLE POSTAGEM 
    ADD CONSTRAINT POSTAGEM_AVALIACAO_FK FOREIGN KEY 
    ( 
     AVALIACAO_id_avaliacao
    ) 
    REFERENCES AVALIACAO 
    ( 
     id_avaliacao
    ) 
;

ALTER TABLE POSTAGEM 
    ADD CONSTRAINT POSTAGEM_GEOLOCALIZACAO_FK FOREIGN KEY 
    ( 
     GEOLOCALIZACAO_id_geolocalizacao
    ) 
    REFERENCES GEOLOCALIZACAO 
    ( 
     id_geolocalizacao
    ) 
;

ALTER TABLE POSTAGEM 
    ADD CONSTRAINT POSTAGEM_PONTUACAO_FK FOREIGN KEY 
    ( 
     PONTUACAO_id_pontuacao
    ) 
    REFERENCES PONTUACAO 
    ( 
     id_pontuacao
    ) 
;

ALTER TABLE POSTAGEM_USUARIO 
    ADD CONSTRAINT POSTAGEM_USUARIO_POSTAGEM_FK FOREIGN KEY 
    ( 
     POSTAGEM_id_postagem,
     POSTAGEM_id_avaliacao
    ) 
    REFERENCES POSTAGEM 
    ( 
     id_postagem,
     AVALIACAO_id_avaliacao
    ) 
;

ALTER TABLE POSTAGEM_USUARIO 
    ADD CONSTRAINT POSTAGEM_USUARIO_USUARIO_FK FOREIGN KEY 
    ( 
     USUARIO_id_usuario
    ) 
    REFERENCES USUARIO 
    ( 
     id_usuario
    ) 
;

ALTER TABLE POSTAGEM 
    ADD CONSTRAINT POSTAGEM_VALIDACAO_FK FOREIGN KEY 
    ( 
     VALIDACAO_id_validacao
    ) 
    REFERENCES VALIDACAO 
    ( 
     id_validacao
    ) 
;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             0
-- ALTER TABLE                             15
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   1
-- WARNINGS                                 0
