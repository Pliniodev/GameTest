package com.pliniodev.gametest.presentation

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pliniodev.gametest.data.local.model.StepModel
import com.pliniodev.gametest.data.local.repository.StepRepository
import com.pliniodev.gametest.domain.usecase.GetPhraseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: GetPhraseUseCase,
    private val database: StepRepository,
    private val shared: SharedPreferences,
    private var sharedEditor: SharedPreferences.Editor
) : ViewModel() {

    val lastSelectedStepLiveData = MutableLiveData<Int>()
    val phraseLiveData = MutableLiveData<String>()
    val updatedStep = MutableLiveData<Int>()
    val errorLiveData = MutableLiveData<String>()
    private val phraseMutableList = mutableListOf<String>()

    fun requestLastStepSelected() {
        val lastStepSelected = shared.getInt("lastStepSelected", 1)
        lastSelectedStepLiveData.postValue(lastStepSelected)
    }

    fun updateSelectedStep(stepSelected: Int) {
        sharedEditor = shared.edit()
        sharedEditor.putInt("lastStepSelected", stepSelected)
        sharedEditor.apply()
        updatedStep.value = stepSelected
    }

    fun getPhrase(stepNumber: Int) {
        phraseLiveData.postValue(database.getPhrase(stepNumber).phrase)
    }

    fun updateBD() {
        if (isOlderOneDay()){
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    useCase()
                }.onSuccess { presentationList ->
                    for (phrase in presentationList) {
                        phraseMutableList.add(phrase.text)
                    }
                    updatePhrasesOnBd(phraseMutableList)
                }.onFailure {
                    errorLiveData.postValue(it.message)
                }
            }
        }
    }

    private fun isOlderOneDay(): Boolean {
        TODO("Not yet implemented")
    }

    private fun updatePhrasesOnBd(phraseMutableList: MutableList<String>) {
        var i = 1
        for (phrase in phraseMutableList) {
            val stepModel = StepModel(
                stepNumber = i,
                phrase = phrase
            )
            i++
            database.saveStepData(stepModel)
        }
    }
}