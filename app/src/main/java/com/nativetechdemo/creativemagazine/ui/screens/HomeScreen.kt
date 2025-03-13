package com.nativetechdemo.creativemagazine.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nativeprojects.utilities.ResourceState
import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import com.nativetechdemo.creativemagazine.ui.components.Loader
import com.nativetechdemo.creativemagazine.ui.components.NewsList
import com.nativetechdemo.creativemagazine.ui.components.NewsRowComponent
import com.nativetechdemo.creativemagazine.ui.viewmodel.NewsViewModel

@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val newsResponse = newsViewModel.news.collectAsState() //updates when new value gets emitted

    val pagerState = rememberPagerState (
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){
        100
    }
    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) {
        page: Int ->
        when (newsResponse.value){
            is ResourceState.Loading -> {
                Log.d(TAG, "Resource::Loading")
                Loader()
            }
            is ResourceState.Success -> {
                val response = (newsResponse.value as ResourceState.Success<NewsResponse>).data
                Log.d(TAG, "Resource::Success:: Total amount of Headers: ${(response.totalResults)}")
                if (response.articles.isNotEmpty()){
                        NewsRowComponent(page, response.articles.get(page))
                    }
            }
            is ResourceState.Error -> {
                Log.d(TAG, "Resource::Error")

            }
        }
    }

//    Surface (
//        modifier = Modifier.fillMaxSize()
//    ) {}
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
const val TAG = "HomeScreen"