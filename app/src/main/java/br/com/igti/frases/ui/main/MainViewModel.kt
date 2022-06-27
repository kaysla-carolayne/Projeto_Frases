package br.com.igti.frases.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.igti.frases.data.Frase
import br.com.igti.frases.data.repository.MemoryRepository

class MainViewModel: ViewModel() {
    private var memoryRepository: MemoryRepository = MemoryRepository (mutableListOf())
    private val _listaDeFrase = MutableLiveData<List<Frase>>()

    val listaDeFrases: LiveData<List<Frase>> = _listaDeFrase

    fun iniciarDados(){
        _listaDeFrase.value = memoryRepository.exibirLista()
    }


    fun salvarFrase(frase: Frase){
        Log.i("IGTIinfo", "Frase recebida: $frase")

        memoryRepository.salvar(frase)
        atualizarListaFrases()
    }


    fun limparListaDeFrases(){
        Log.i( "IGTIinfo:","Iniciando processo de limpeza do repositório")
        memoryRepository.limparLista()
        atualizarListaFrases()
    }

    private fun atualizarListaFrases() {
        _listaDeFrase.value = memoryRepository.exibirLista()
    }
}