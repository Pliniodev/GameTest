package com.pliniodev.gametest.data.local.mapper

import com.pliniodev.gametest.data.local.model.StepEntity
import com.pliniodev.gametest.domain.model.StepDomain

internal object StepMapper {

    fun stepToDomain(source: StepEntity): StepDomain {
        return StepDomain(
            id = source.id,
            stepNumber = source.stepNumber,
            phrase = source.phrase
        )
    }
}