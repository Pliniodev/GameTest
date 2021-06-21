package com.pliniodev.gametest.data.local.repository

import com.pliniodev.gametest.data.local.dao.StepDAO
import com.pliniodev.gametest.data.local.model.StepModel

class StepRepositoryImpl(
    private val database: StepDAO
) : StepRepository {
    override fun saveStepData(stepModel: StepModel): Boolean {
        return database.saveStepData(stepModel) > 0
    }

    override fun getPhrase(stepNumber: Int): StepModel {
        return database.getPhrase(stepNumber)
    }
}