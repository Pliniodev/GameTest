package com.pliniodev.gametest.domain.usecase

import com.pliniodev.gametest.data.local.model.StepEntity
import com.pliniodev.gametest.domain.repository.StepRepository

class UpdateDBUseCase(
    private val repository: StepRepository
) {
    operator fun invoke(stepEntity: StepEntity) =
        repository.updateDB(stepEntity)
}