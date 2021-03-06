package com.pliniodev.gametest.presentation

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pliniodev.gametest.data.local.model.StepEntity
import com.pliniodev.gametest.domain.usecase.ApiUseCase
import com.pliniodev.gametest.domain.usecase.DBUseCase
import com.pliniodev.gametest.domain.usecase.UpdateDBUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class MainViewModel(
    private val apiUseCase: ApiUseCase,
    private val dbPhraseUseCase: DBUseCase,
    private val updateDBUseCase: UpdateDBUseCase,
    private val shared: SharedPreferences,
    private var sharedEditor: SharedPreferences.Editor
    //aaa
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
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                dbPhraseUseCase(stepNumber)
            }.onSuccess {
                phraseLiveData.postValue(it.phrase)
            }.onFailure {
                errorLiveData.postValue(it.message)
            }
        }
    }

    fun updateBD(firstRun: Boolean) {
        if (isOlderOneDay() || firstRun) {
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    apiUseCase()
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

    private suspend fun updatePhrasesOnBd(phraseMutableList: MutableList<String>) {
        var i = 1
        for (phrase in phraseMutableList) {
            val stepModel = StepEntity(
                stepNumber = i,
                phrase = phrase
            )
            i++
            updateDBUseCase(stepModel)
        }
    }

    private fun isOlderOneDay(): Boolean {
        val lastAccessDay = shared.getInt("lastAccessDay", 0)
        val now = getDate().dayOfMonth().get()
        return (lastAccessDay - now) > 1
    }

    fun registerDate() {
        val lastDayAccess = getDate().dayOfMonth().get()
        sharedEditor = shared.edit()
        sharedEditor.putInt("lastAccessDay", lastDayAccess)
        sharedEditor.apply()
    }

    private fun getDate(): DateTime {
        val dt = DateTime()
        return dt.withZone(DateTimeZone.forID("America/Sao_Paulo"))
    }

}