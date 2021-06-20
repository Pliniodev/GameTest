package com.pliniodev.gametest.data.remote.model

import com.google.gson.annotations.SerializedName

data class PhraseResponse(
    @SerializedName("text") val text: String
)