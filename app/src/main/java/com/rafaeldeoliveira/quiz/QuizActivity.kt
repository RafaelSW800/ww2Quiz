package com.rafaeldeoliveira.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafaeldeoliveira.quiz.databinding.ActivityPerguntasBinding
import kotlin.system.exitProcess

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerguntasBinding

    // Variável para controlar o índice da pergunta atual
    private var indicePergunta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerguntasBinding.inflate(layoutInflater)
        val view = binding.root

        // Chamar função para exibir a primeira pergunta
        exibirPergunta()

        // Configurar o botão para validar a resposta
        binding.btnResponder.setOnClickListener {
            verificarResposta()
        }

        setContentView(view)
    }

    private fun exibirPergunta() {
        val perguntaAtual = perguntasEmbaralhadas[indicePergunta]
        if (indicePergunta >= 0 && indicePergunta < perguntas.size) {
            binding.apply {
                tvPergunta.text = perguntaAtual.texto
                rbOpcao1.text = perguntaAtual.opcoes[0]
                rbOpcao2.text = perguntaAtual.opcoes[1]
                rbOpcao3.text = perguntaAtual.opcoes[2]

                // Limpar a seleção anterior do RadioGroup
                rgOpcoes.clearCheck()
            }
        }
    }

    private fun verificarResposta() {
        val rgOpcoes = findViewById<RadioGroup>(R.id.rgOpcoes)
        val selecionadoId = rgOpcoes.checkedRadioButtonId

        // Identifica o índice da resposta selecionada
        val indiceSelecionado = when (selecionadoId) {
            R.id.rbOpcao1 -> 0
            R.id.rbOpcao2 -> 1
            R.id.rbOpcao3 -> 2
            else -> -1
        }

        // Verifica se a resposta está correta
        if (indiceSelecionado == perguntasEmbaralhadas[indicePergunta].respostaCorreta) {
            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show()
            indicePergunta++
            if (indicePergunta < perguntasEmbaralhadas.size) {
                exibirPergunta()
            } else {
                // Fim do jogo - encaminhar para a tela de "parabéns"
                val intent = Intent(this, FimDoJogo::class.java)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "Resposta incorreta, reiniciando!", Toast.LENGTH_SHORT).show()
            exitProcess(0)
            finish()

        }
    }
}