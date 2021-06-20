package com.pliniodev.gametest.data.remote.remotedatasource

import com.pliniodev.gametest.domain.model.PhraseDomain

interface RemoteDataSource {

    suspend fun getPhrases(): List<PhraseDomain>
}