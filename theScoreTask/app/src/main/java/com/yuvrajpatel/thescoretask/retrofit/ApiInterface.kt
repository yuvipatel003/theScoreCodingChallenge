package com.yuvrajpatel.thescoretask.retrofit

import com.yuvrajpatel.thescoretask.model.Team
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("input.json")
    fun getTeamList(): Call<List<Team>>

    companion object {

        val BASE_URL = "https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}