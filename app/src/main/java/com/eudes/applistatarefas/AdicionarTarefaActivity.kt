package com.eudes.applistatarefas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eudes.applistatarefas.database.TarefaDAO
import com.eudes.applistatarefas.databinding.ActivityAdicionarTarefaBinding
import com.eudes.applistatarefas.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recuperar tarefa passada para ser editada
        val bundle = intent.extras
        var tarefa: Tarefa? = null
        if (bundle != null){
            tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTextAdicionar.setText(tarefa.descricao)
        }


        binding.btnSalvar.setOnClickListener {
            if (binding.editTextAdicionar.text.toString().isNotEmpty()) {
                if (tarefa != null){
                    /*passando apenas o id da tarefa que não será modificado, e que é necessário
                    para poder identificar para especificar qual a terfa que está sendo atualizada*/
                    editar(tarefa)
                }else{
                    salvar()
                }
            } else {
                Toast.makeText(this, "Adicione uma tarefa para salvar", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun editar(tarefa: Tarefa) {
        val descricaoAtualizar = binding.editTextAdicionar.text.toString()

        val tarefaDAO = TarefaDAO(this)
        val tarefaAtualizar = Tarefa(tarefa.id_tarefa, descricaoAtualizar, "")

        if (tarefaDAO.atualizar(tarefaAtualizar)){
            Toast.makeText(this, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun salvar() {
        val descricao = binding.editTextAdicionar.text.toString()
        //criando uma conexão com o banco de dados
        val tarefaDAO = TarefaDAO(this)
        val tarefa = Tarefa(-1, descricao, "")

        if (tarefaDAO.salvar(tarefa)) {
            Toast.makeText(this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}