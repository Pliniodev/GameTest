package com.pliniodev.gametest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pliniodev.gametest.constants.Constants
import com.pliniodev.gametest.domain.usecase.GetPhraseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: GetPhraseUseCase
): ViewModel() {
    val lastSelectedStepLiveData = MutableLiveData<Int>()
    val phraseLiveData = MutableLiveData<String>()
    val updatedStep = MutableLiveData<Int>()
    val errorLiveData = MutableLiveData<String>()
    private val phraseMutableList = mutableListOf<String>()

    /**
     * Requisita o ultimo step selecionado no bd, o padrão é 0
     */
    fun requestLastStepSelected() {//requisita o último step selecionado bd
        lastSelectedStepLiveData.value = Constants.STEP1//caso retorne 1
    }

    fun updateSelectedStep(stepSelected: Int) { //escreve no banco de dados
        updatedStep.value = stepSelected
    }

    //request ao banco de dados
    fun getPhrase(step: Int) {

    }

    fun updateBD() {
        //todo passou um dia?
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                useCase()
            }.onSuccess { presentationList ->
                for (phrase in presentationList){
                    phraseMutableList.add(phrase.text)
                }
                updatePhrasesOnBd(phraseMutableList)
            }.onFailure {
                errorLiveData.postValue(it.message)
            }
        }
    }

    private fun updatePhrasesOnBd(phraseMutableList: MutableList<String>) {

    }

}