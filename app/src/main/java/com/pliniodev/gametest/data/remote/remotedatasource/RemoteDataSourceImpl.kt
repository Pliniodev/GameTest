package com.pliniodev.gametest.data.remote.remotedatasource

import com.pliniodev.gametest.data.remote.api.ApiService
import com.pliniodev.gametest.data.remote.mapper.PhraseMapper
import com.pliniodev.gametest.domain.model.PhraseDomain

class RemoteDataSourceImpl(
    private val api: ApiService
) : RemoteDataSource {

    override suspend fun getPhrases(): List<PhraseDomain> {
        return PhraseMapper.phraseToDomain(api.getPhrases())
    }
}