package com.nativetechdemo.creativemagazine.data.datasource

import com.nativetechdemo.creativemagazine.data.AppConstants
import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        // key held in code just for minor demo purposes
        @Query("apiKey") apiKey: String = AppConstants.API_KEY_LOCAL
    ) : Response<NewsResponse>
}
