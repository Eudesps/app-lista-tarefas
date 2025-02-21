package com.eudes.applistatarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eudes.applistatarefas.databinding.ItemTarefaBinding
import com.eudes.applistatarefas.model.Tarefa

class TarefaAdapter(
    private val onCliqueExcluir: (Int) -> Unit,
    private val onCliqueAtualizar: (tarefa: Tarefa) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listaTarefas: List<Tarefa> = emptyList()

    fun adicionarLista(lista: List<Tarefa>) {
        this.listaTarefas = lista
        notifyDataSetChanged()
    }

    inner class TarefaViewHolder(itemBinding: ItemTarefaBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTarefaBinding

        init {
            binding = itemBinding
        }

        fun bind(tarefa: Tarefa){
            binding.textDescricao.text = tarefa.descricao
            binding.textData.text = tarefa.dataCadastro

            binding.btnExcluir.setOnClickListener {
                onCliqueExcluir(tarefa.id_tarefa)
            }
            binding.btnEditar.setOnClickListener {
                onCliqueAtualizar(tarefa)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val itemTarefaBinding = ItemTarefaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TarefaViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]
        holder.bind(tarefa)
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }

}