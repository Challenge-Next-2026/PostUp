
import os
import oracledb
from dotenv import load_dotenv

load_dotenv()

def conectar():
    try:
        conexao = oracledb.connect(
            user=os.getenv("DB_USER"),
            password=os.getenv("DB_PASSWORD"),
            dsn=os.getenv("DB_DSN")
        )

        print("Conexão com Oracle realizada com sucesso!")
        return conexao

    except oracledb.Error as erro:
        print("Erro ao conectar:", erro)
        return None