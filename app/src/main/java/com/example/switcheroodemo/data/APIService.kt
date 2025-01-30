package com.example.switcheroodemo.data


import com.example.switcheroodemo.ui.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("v2/everything")
    suspend fun getUtilityNews(
        @Query("q") query: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}