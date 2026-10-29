
from pathlib import Path

import joblib
import pandas as pd

from fastapi import FastAPI
from pydantic import BaseModel, Field


# Caminho dos arquivos
PASTA = Path(__file__).resolve().parent

modelo_validacao = joblib.load(
    PASTA / "modelo_validacao_postup.joblib"
)

modelo_score = joblib.load(
    PASTA / "modelo_score_postup.joblib"
)


# Criar a aplicação
app = FastAPI(
    title="PostUp - API de Machine Learning",
    description=(
        "API para classificação de postagens "
        "e estimativa do Score."
    ),
    version="1.0.0"
)


# Dados recebidos para classificar uma postagem
class DadosValidacao(BaseModel):
    Duplicidade: str
    Plágio: str
    Indicador_IA: str = Field(alias="Indicador IA")
    Evidencia_Compativel: str = Field(
        alias="Evidência Compativel"
    )
    Veracidade: str
    Origem_do_Registro: str = Field(
        alias="Origem do Registro"
    )

    model_config = {"populate_by_name": True}


# Dados recebidos para estimar o Score
class DadosScore(BaseModel):
    Impacto: float = Field(ge=0, le=100)
    Frequência: float = Field(ge=0, le=100)
    Dificuldade: float = Field(ge=0, le=100)
    Confiabilidade: float = Field(ge=0, le=100)


@app.get("/")
def inicio():
    return {
        "mensagem": "API do PostUp funcionando!",
        "documentacao": "/docs"
    }


@app.post("/validar")
def validar_postagem(dados: DadosValidacao):
    entrada = pd.DataFrame([{
        "Duplicidade": dados.Duplicidade,
        "Plágio": dados.Plágio,
        "Indicador IA": dados.Indicador_IA,
        "Evidência Compativel": dados.Evidencia_Compativel,
        "Veracidade": dados.Veracidade,
        "Origem do Registro": dados.Origem_do_Registro
    }])

    previsao = modelo_validacao.predict(entrada)

    return {
        "classificacao": str(previsao[0])
    }


@app.post("/score")
def calcular_score(dados: DadosScore):
    entrada = pd.DataFrame([{
        "Impacto": dados.Impacto,
        "Frequência": dados.Frequência,
        "Dificuldade": dados.Dificuldade,
        "Confiabilidade": dados.Confiabilidade
    }])

    previsao = modelo_score.predict(entrada)

    return {
        "score_previsto": round(float(previsao[0]), 2)
    }
