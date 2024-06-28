package com.eudes.applistatarefas.database

import com.eudes.applistatarefas.model.Tarefa

interface ITarefaDAO {
    fun salvar(tarefa: Tarefa): Boolean
    fun atualizar(tarefa: Tarefa): Boolean
    fun remover(id_tarefa: Int): Boolean
    fun listar(): List<Tarefa>

}