package com.nativetechdemo.creativemagazine.data.repositories

import com.nativeprojects.utilities.ResourceState
import com.nativetechdemo.creativemagazine.data.datasource.NewsDataSource
import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
){

//    suspend fun getNewsHeadline(country: String) : Response<NewsResponse> {
//        return newsDataSource.getNewsHeadline(country)
//    }

    suspend fun getNewsHeadline(country: String) : Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = newsDataSource.getNewsHeadline(country)

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error Fetching the Data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e.localizedMessage ?: "Error Fetching the Data"))
        }
    }
}