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
        binding.btnSalvar.setOnClickListener {
            if (binding.editTextAdicionar.text.toString().isNotEmpty()){
                val descricao = binding.editTextAdicionar.text.toString()
                //criando uma conexão com o banco de dados
                val tarefaDAO = TarefaDAO(this)
                val tarefa = Tarefa(-1, descricao, "")

                if(tarefaDAO.salvar(tarefa)){
                    Toast.makeText(this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }else{
                Toast.makeText(this, "Adicione uma tarefa para salvar", Toast.LENGTH_SHORT).show()
            }

        }

    }
}