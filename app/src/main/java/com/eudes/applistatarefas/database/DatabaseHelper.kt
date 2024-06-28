package com.eudes.applistatarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS) {
    companion object {
        const val NOME_BANCO_DADOS = "ListaTarefas.db"
        const val VERSAO_BANCO_DADOS = 1
        const val NOME_TABELA_TAREFAS = "tarefas"
        const val COLUNA_ID_TAREFA = "id_tarefa"
        const val COLUNA_DESCICAO = "descricao"
        const val COLUNA_DATA_CADASTRO = "data_cadastro"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $NOME_TABELA_TAREFAS(" +
                "$COLUNA_ID_TAREFA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$COLUNA_DESCICAO VARCHAR(70)," +
                "$COLUNA_DATA_CADASTRO DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP);"

        try {
            db?.execSQL(sql)
            Log.i("info.db", "Banco de dados criado com sucesso!")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info.db", "Erro ao criar o banco de dados: $e")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}

