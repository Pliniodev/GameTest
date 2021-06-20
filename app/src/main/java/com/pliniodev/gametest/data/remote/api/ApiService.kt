package com.pliniodev.gametest.data.remote.api

import com.pliniodev.gametest.data.remote.model.PhraseResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/facts")
    suspend fun getPhrases(): List<PhraseResponse>
}