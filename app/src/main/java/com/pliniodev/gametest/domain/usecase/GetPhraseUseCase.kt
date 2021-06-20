package com.pliniodev.gametest.domain.usecase

import com.pliniodev.gametest.domain.repository.ApiRepository

class GetPhraseUseCase(
    private val repository: ApiRepository
) {

    suspend operator fun invoke() = repository.getPhrase()
}