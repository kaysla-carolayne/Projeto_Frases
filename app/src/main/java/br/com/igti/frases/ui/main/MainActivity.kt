package br.com.igti.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import br.com.igti.frases.R
import br.com.igti.frases.data.Frase
import br.com.igti.frases.databinding.ActivityMainBinding
import br.com.igti.frases.ui.incluirfrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val retornoFrase = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ activityResult ->
        if ( activityResult.resultCode == RESULT_OK){
            activityResult.data?. let {
                if(it.hasExtra(RETORNO)) {
                    Log.i("IGTIinfo:","Retorno:${it.getParcelableExtra<Frase>(RETORNO)}")
                    viewModel.salvarFrase(it.getParcelableExtra<Frase>(RETORNO)!!)
                }
            }
        } else{
            Log.e ("IGTIerror:", "Não foi possivel obter os dados de retorno da activity. ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        iniciarDados()

        configurarFabListener()
        configurarObservers()
    }

    private fun configurarObservers() {
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        viewModel.listaDeFrases.observe(this){  lista->
            Log.i("JPInfo:", "Frase observada: $lista")
            atualizarLista(lista)
        }
    }

    private fun atualizarLista(lista: List<Frase>) {
        if (lista.isNullOrEmpty()){
            binding.rvListaFrase.visibility = View.GONE
            binding.tvMensagemListaVazia.visibility = View.VISIBLE
        } else {
            binding.tvMensagemListaVazia.visibility = View.GONE
            binding.rvListaFrase.visibility = View.VISIBLE
            binding.rvListaFrase.adapter = FrasesAdapter(listaDeFrases = lista)
        }
    }

    private fun iniciarDados() {
        viewModel.iniciarDados()
    }

    private fun configurarListeners() {
        configurarFabListener()
    }

    private fun configurarFabListener() {
        binding.fabAddNovaFrase.setOnClickListener {
            Intent ( this, IncluirFraseActivity::class.java).let{
                retornoFrase.launch(it)
            }
        }

        binding.fabAddNovaFrase.setOnLongClickListener {
            viewModel.limparListaDeFrases()
            Toast.makeText(
                this, R.string.lista_limpa_sucesso,
            Toast.LENGTH_LONG
            ).show()
            it.isLongClickable
        }


    }
    companion object{
        const val RETORNO = "Retorno_frases"
    }
}