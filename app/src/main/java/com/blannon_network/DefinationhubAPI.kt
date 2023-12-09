package com.blannon_network

import com.blannon_network.Respond.WordResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DefinationhubAPI {
    @GET("en/{word}")

    suspend fun getMeaning(@Path("word") word : String) : Response<List<WordResult>>

}