package com.nativetechdemo.creativemagazine.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nativeprojects.utilities.ResourceState
import com.nativetechdemo.creativemagazine.data.AppConstants
import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import com.nativetechdemo.creativemagazine.data.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _news: MutableStateFlow<ResourceState<NewsResponse>> =
        MutableStateFlow(ResourceState.Loading())
    val news: StateFlow<ResourceState<NewsResponse>> = _news // state flow can't be changed

    init {
        getNews(AppConstants.COUNTRY)
        Log.d(TAG, "NewsViewModel::Init::ViewModel attached and initialized")
    }

    private fun getNews(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.getNewsHeadline(country)
                .collectLatest { // extension function for the flows
                    newsResponse ->
                    _news.value = newsResponse
                }
        }
    }

    companion object {
        val TAG = javaClass.simpleName
    }
}