package com.pliniodev.gametest.domain.repository

import com.pliniodev.gametest.presentation.model.PhrasePresentation

interface ApiRepository {

    suspend fun getPhrase(): List<PhrasePresentation>
}