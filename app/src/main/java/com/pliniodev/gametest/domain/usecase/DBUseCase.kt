package com.pliniodev.gametest.domain.usecase

import com.pliniodev.gametest.domain.repository.StepRepository
import com.pliniodev.gametest.presentation.model.StepPresentation

class DBUseCase(
    private val repository: StepRepository,
) {
    operator fun invoke(stepNumber: Int): StepPresentation = repository
        .getPhraseDB(stepNumber)
}