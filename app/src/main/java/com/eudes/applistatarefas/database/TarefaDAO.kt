package com.eudes.applistatarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.eudes.applistatarefas.model.Tarefa

class TarefaDAO(context: Context): ITarefaDAO {
    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase
    override fun salvar(tarefa: Tarefa): Boolean {

        try {
            val conteudos = ContentValues()
            conteudos.put("${DatabaseHelper.COLUNA_DESCICAO}", "${tarefa.descricao}")

            escrita.insert(DatabaseHelper.NOME_TABELA_TAREFAS, null, conteudos)

            Log.i("info.db", "Item salvo com sucesso")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info.db", "Erro ao criar o banco de dados: $e")
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun remover(id_tarefa: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
        TODO("Not yet implemented")
    }
}