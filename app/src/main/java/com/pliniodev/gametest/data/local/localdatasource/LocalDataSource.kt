package com.pliniodev.gametest.data.local.localdatasource

import com.pliniodev.gametest.data.local.model.StepEntity
import com.pliniodev.gametest.domain.model.StepDomain

interface LocalDataSource {

    fun updateDB(stepEntity: StepEntity): Long

    fun getPhraseDB(stepNumber: Int): StepDomain
}