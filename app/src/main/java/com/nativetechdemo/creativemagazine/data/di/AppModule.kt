package com.nativetechdemo.creativemagazine.data.di

import com.nativetechdemo.creativemagazine.data.AppConstants
import com.nativetechdemo.creativemagazine.data.datasource.ApiService
import com.nativetechdemo.creativemagazine.data.datasource.NewsDataSource
import com.nativetechdemo.creativemagazine.data.datasource.NewsDataSourceImpl
import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import com.nativetechdemo.creativemagazine.data.repositories.ArticleRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit () : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConstants.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsDataSource(apiService: ApiService) : NewsDataSource {
        return NewsDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesArticleRepository(newsDataSource: NewsDataSource): ArticleRepository {
        return ArticleRepository(newsDataSource)

    }
}
