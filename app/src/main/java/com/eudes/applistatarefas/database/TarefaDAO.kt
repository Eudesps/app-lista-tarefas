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
        try {
            val conteudo = ContentValues()
            conteudo.put("${DatabaseHelper.COLUNA_DESCICAO}", tarefa.descricao)

            //onde essa atualização vai ser salva
            val args = arrayOf(tarefa.id_tarefa.toString())

            escrita.update(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                conteudo,
                "${DatabaseHelper.COLUNA_ID_TAREFA} = ?",
                args
            )
            Log.i("info.db", "Item atualizado com sucesso")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info.db", "Erro ao atualizar tarefa: $e")
            return false
        }
        return true
    }

    override fun remover(id_tarefa: Int): Boolean {
        val args = arrayOf(id_tarefa.toString())
        try {
            escrita.delete(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                "${DatabaseHelper.COLUNA_ID_TAREFA} =  ?",
                args
            )

            Log.i("info.db", "Item excluído com sucesso")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info.db", "Erro ao excluir tarefa: $e")
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {
        val listatarefas = mutableListOf<Tarefa>()

        //consulta (faz a seleção) o banco de dados
        val sql = "SELECT ${DatabaseHelper.COLUNA_ID_TAREFA}, ${DatabaseHelper.COLUNA_DESCICAO}," +
                " strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.COLUNA_DATA_CADASTRO}) AS ${DatabaseHelper.COLUNA_DATA_CADASTRO} " +
                "FROM ${DatabaseHelper.NOME_TABELA_TAREFAS}"

        // usa o cursor para recuperar os dados
        val cursor = leitura.rawQuery(sql, null)

        //captura os indices de cada coluna
        val indiceId = cursor.getColumnIndex(DatabaseHelper.COLUNA_ID_TAREFA)
        val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.COLUNA_DESCICAO)
        val indiceData = cursor.getColumnIndex(DatabaseHelper.COLUNA_DATA_CADASTRO)

        //depois percorre utilizando o while
        while (cursor.moveToNext()){
            val idTarefa = cursor.getInt(indiceId)
            val descricao = cursor.getString(indiceDescricao)
            val data_cadastro = cursor.getString(indiceData)

            listatarefas.add(Tarefa(idTarefa, descricao, data_cadastro))
        }
        
        return listatarefas
    }
}