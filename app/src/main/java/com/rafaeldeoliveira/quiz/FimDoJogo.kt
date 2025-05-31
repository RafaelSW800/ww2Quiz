package com.rafaeldeoliveira.quiz

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class FimDoJogo : AppCompatActivity() {
    
    private var mediaPlayer: MediaPlayer? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fim_do_jogo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Iniciar a música de fundo
        iniciarMusicaFundo()
        
        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            exitProcess(0)
            finish()
        }
    }
    
    private fun iniciarMusicaFundo() {
        mediaPlayer = MediaPlayer.create(this, R.raw.erika)
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
}