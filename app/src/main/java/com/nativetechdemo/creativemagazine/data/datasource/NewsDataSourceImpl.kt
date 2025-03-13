package com.nativetechdemo.creativemagazine.data.datasource

import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiSourceImpl: ApiService
): NewsDataSource {
    override suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
        return apiSourceImpl.getNewsHeadline(country)
    }
}
