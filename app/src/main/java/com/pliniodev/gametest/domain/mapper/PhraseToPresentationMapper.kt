package com.pliniodev.gametest.domain.mapper

import com.pliniodev.gametest.domain.model.PhraseDomain
import com.pliniodev.gametest.presentation.model.PhrasePresentation

internal object PhraseToPresentationMapper {

    fun mapPhrase(source: List<PhraseDomain>): List<PhrasePresentation> {
        return source.map { PhraseResponse ->
            PhrasePresentation(
                text = PhraseResponse.text
            )
        }
    }
}
