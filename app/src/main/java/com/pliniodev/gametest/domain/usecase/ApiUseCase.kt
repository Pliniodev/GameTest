package com.pliniodev.gametest.domain.usecase

import com.pliniodev.gametest.domain.repository.ApiRepository

class ApiUseCase(
    private val repository: ApiRepository
) {
    suspend operator fun invoke() = repository.getPhrase()
}