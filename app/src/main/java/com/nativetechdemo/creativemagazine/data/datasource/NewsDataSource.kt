package com.nativetechdemo.creativemagazine.data.datasource

import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {
    suspend fun getNewsHeadline(country: String) : Response<NewsResponse>
}