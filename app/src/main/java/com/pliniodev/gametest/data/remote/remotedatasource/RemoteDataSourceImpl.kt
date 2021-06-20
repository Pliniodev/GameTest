package com.pliniodev.gametest.data.remote.remotedatasource

import com.pliniodev.gametest.data.remote.api.ApiService
import com.pliniodev.gametest.data.remote.mapper.PhraseToDomainMapper

class RemoteDataSourceImpl(
    private val api: ApiService
) : RemoteDataSource {

    private val mapper: PhraseToDomainMapper = PhraseToDomainMapper()

    override suspend fun getPhrases() = mapper.map(api.getPhrases())
}