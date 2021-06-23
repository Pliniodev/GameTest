package com.pliniodev.gametest.domain.mapper

import com.pliniodev.gametest.domain.model.StepDomain
import com.pliniodev.gametest.presentation.model.StepPresentation

internal object StepToPresentation {

    fun mapStep(source: StepDomain): StepPresentation {
        return StepPresentation(
            id = source.id,
            stepNumber = source.stepNumber,
            phrase = source.phrase
        )
    }
}