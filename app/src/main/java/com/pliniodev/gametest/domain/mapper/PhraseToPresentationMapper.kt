package com.pliniodev.gametest.domain.mapper

import com.pliniodev.gametest.domain.model.PhraseDomain
import com.pliniodev.gametest.presentation.model.PhrasePresentation
import com.pliniodev.gametest.utils.Mapper

class PhraseToPresentationMapper: Mapper<PhraseDomain, PhrasePresentation> {

//    override fun map(source: List<PhraseResponse>): List<PhrasePresentation> {
//        return source.map { PhraseResponse ->
//            PhrasePresentation(
//                text = PhraseResponse.text
//            )
//        }
//    }
//
//    override fun map(source: List<List<PhraseDomain>>): List<List<PhrasePresentation>> {
//        TODO("Not yet implemented")
//    }

    override fun map(source: List<PhraseDomain>): List<PhrasePresentation> {
        return source.map { PhraseResponse ->
            PhrasePresentation(
                text = PhraseResponse.text
            )
        }
    }
}