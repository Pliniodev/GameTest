package com.pliniodev.gametest.data.remote.repository

import com.pliniodev.gametest.data.remote.remotedatasource.RemoteDataSource
import com.pliniodev.gametest.domain.mapper.PhraseToPresentationMapper
import com.pliniodev.gametest.domain.repository.ApiRepository
import com.pliniodev.gametest.presentation.model.PhrasePresentation

class ApiRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ApiRepository {

    private val mapper: PhraseToPresentationMapper = PhraseToPresentationMapper()

    override suspend fun getPhrase(): List<PhrasePresentation> {
        return mapper.map(remoteDataSource.getPhrases())
    }
}