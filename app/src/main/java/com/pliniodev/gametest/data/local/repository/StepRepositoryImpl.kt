package com.pliniodev.gametest.data.local.repository

import com.pliniodev.gametest.data.local.localdatasource.LocalDataSource
import com.pliniodev.gametest.data.local.model.StepEntity
import com.pliniodev.gametest.domain.mapper.StepToPresentation
import com.pliniodev.gametest.domain.repository.StepRepository
import com.pliniodev.gametest.presentation.model.StepPresentation

class StepRepositoryImpl(
    private val localDataSource: LocalDataSource
) : StepRepository {

    override fun updateDB(stepEntity: StepEntity): Long {
        return localDataSource.updateDB(stepEntity)
    }

    override fun getPhraseDB(stepNumber: Int): StepPresentation {
        return StepToPresentation.mapStep(localDataSource.getPhraseDB(stepNumber))
    }
}