package br.com.igti.frases.ui.incluirfrase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.igti.frases.R
import br.com.igti.frases.data.Frase
import br.com.igti.frases.databinding.ActivityIncluirFraseBinding
import br.com.igti.frases.ui.main.MainActivity

class IncluirFraseActivity : AppCompatActivity() {

    private lateinit var binding:ActivityIncluirFraseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncluirFraseBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        configurarListeners()
    }

    private fun configurarListeners() {
        configurarBotaoCancelarListener()
        configurarBotaoSalvarListener()
    }

    private fun configurarBotaoSalvarListener() {
        binding.btnSalvar.setOnClickListener{
            salvarFrases()
        }
    }

    private fun configurarBotaoCancelarListener() {
        binding.btnCancelar.setOnClickListener{ finish()}
    }

    private fun salvarFrases() {

        binding.apply {
            val autor = autoresfraseED.text.toString()
            val frase = frasesET.text.toString()

            autoresfraseTIL.error =  if (autor.isEmpty()){
              getString(R.string.erro_nome_autor)
            }else {
                null
            }

            fraseTIL.error =  if (autor.isEmpty()){
                getString(R.string.erro_frase_nao_preenchida)
            } else{
                null
            }

            if (autor.isNotEmpty()&& frase.isNotEmpty()){
                Log.i("IGTIinfo","Autor:$autor, Frase: $frase")
                Intent().apply {
                    putExtra(MainActivity.RETORNO, Frase(
                        autor = autor,
                        frase = frase
                    ))
                }
                finish()
            }else
                Log.i("IGTIinfo","Dados não salvos, os campos estavam vazios.")

        }

    }
}
