package com.pliniodev.gametest.data.local.repository

import com.pliniodev.gametest.data.local.model.StepModel

interface StepRepository {

    fun saveStepData(stepModel: StepModel): Boolean

//    fun getPhrase(stepNumber: Int): StepModel
}