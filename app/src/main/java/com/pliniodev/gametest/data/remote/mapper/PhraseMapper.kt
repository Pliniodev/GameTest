package com.pliniodev.gametest.data.remote.mapper

import com.pliniodev.gametest.data.remote.model.PhraseResponse
import com.pliniodev.gametest.domain.model.PhraseDomain

internal object PhraseMapper {

    fun phraseToDomain(source: List<PhraseResponse>): List<PhraseDomain> {

        return source.map { PhraseResponse ->
            PhraseDomain(
                text = PhraseResponse.text
            )
        }
    }
}

