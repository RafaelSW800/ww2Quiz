package com.rafaeldeoliveira.quiz

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafaeldeoliveira.quiz.databinding.ActivityPerguntasBinding
import kotlin.system.exitProcess

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerguntasBinding
    private var mediaPlayer: MediaPlayer? = null
    private var isMuted = false

    // Variável para controlar o índice da pergunta atual
    private var indicePergunta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerguntasBinding.inflate(layoutInflater)
        val view = binding.root

        // Iniciar a música de fundo
        iniciarMusicaFundo()

        // Chamar função para exibir a primeira pergunta
        exibirPergunta()

        // Configurar o botão para validar a resposta
        binding.btnResponder.setOnClickListener {
            verificarResposta()
        }

        // Configurar o botão para desistir
        binding.btnDesistir.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Adiciona flags para limpar a pilha de atividades e iniciar MainActivity como uma nova tarefa
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            exitProcess(0)
            finish()
        }

        binding.btnMutar.setOnClickListener{
            toggleMusicMuteState()
        }

        setContentView(view)
    }

    private fun iniciarMusicaFundo() {
        mediaPlayer = MediaPlayer.create(this, R.raw.katyusha)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos do MediaPlayer quando a Activity for destruída
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onPause() {
        super.onPause()
        // Pausar a música quando a Activity não estiver em foco
        mediaPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        // Retomar a música quando a Activity voltar ao foco
        mediaPlayer?.start()
    }
    
    private fun toggleMusicMuteState() {
        mediaPlayer?.let { mp ->
             isMuted = !isMuted // Toggle the desired mute state

            if (isMuted) {
                // Aplicar mudo
                mp.setVolume(0.0f, 0.0f)
                binding.btnMutar.setImageResource(android.R.drawable.ic_lock_silent_mode)
            } else {
                //Remover mudo
                mp.setVolume(1.0f, 1.0f)
                binding.btnMutar.setImageResource(android.R.drawable.ic_lock_silent_mode_off)
            }
        }
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
