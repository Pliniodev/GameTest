package com.pliniodev.gametest.domain.repository

import com.pliniodev.gametest.data.local.model.StepEntity
import com.pliniodev.gametest.presentation.model.StepPresentation

interface StepRepository {

    fun updateDB(stepEntity: StepEntity): Long

    fun getPhraseDB(stepNumber: Int): StepPresentation
}