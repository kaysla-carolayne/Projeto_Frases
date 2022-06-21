package br.com.igti.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.igti.frases.databinding.ActivityMainBinding
import br.com.igti.frases.ui.incluirfrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configurarFabListener()
    }

    private fun configurarListeners() {
        configurarFabListener()
    }

    private fun configurarFabListener() {
        binding.fabAddNovaFrase.setOnClickListener {
            val intent = Intent( this, IncluirFraseActivity::class.java)
            startActivity(intent)
        }
    }
}