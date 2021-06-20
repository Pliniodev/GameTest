package com.pliniodev.gametest.data.remote.mapper

import com.pliniodev.gametest.data.remote.model.PhraseResponse
import com.pliniodev.gametest.domain.model.PhraseDomain
import com.pliniodev.gametest.utils.Mapper

class PhraseToDomainMapper: Mapper<PhraseResponse, PhraseDomain> {

    override fun map(source: List<PhraseResponse>): List<PhraseDomain> {

        return source.map { PhraseResponse ->
            PhraseDomain(
                text = PhraseResponse.text
            )
        }
    }


}