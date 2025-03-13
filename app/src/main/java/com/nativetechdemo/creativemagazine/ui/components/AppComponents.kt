package com.nativetechdemo.creativemagazine.ui.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.creativemagazine.R
import com.nativetechdemo.creativemagazine.data.AppConstants
import com.nativetechdemo.creativemagazine.data.entity.Articles
import com.nativetechdemo.creativemagazine.data.entity.NewsResponse
import com.nativetechdemo.creativemagazine.ui.theme.Purple40

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(150.dp)
                .padding(10.dp),
            color = Purple40
        )
    }
}

@Composable
fun NewsList(response: NewsResponse) {
    LazyColumn {
        items(response.articles) { article ->
            NormalTextComponent(textvalue = article.title ?: "NA")
        }
    }
}

@Composable
fun NormalTextComponent(textvalue: String) {
    Text(
        text = textvalue,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            color = Purple40
        )
    )
}

@Composable
fun HeadingTextComponent(textvalue: String) {
    Text(
        text = textvalue,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun NewsRowComponent(page: Int, article: Articles) {
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(8.dp)
            .background(Color.White)
    ) {
        val loremIpsum = LoremIpsum(250)
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.cityscape_placeholder),
            error = painterResource(R.drawable.cityscape_placeholder)
        )

        Spacer(modifier = Modifier.size(20.dp))

        HeadingTextComponent(article.title.toString() ?: "")

        Spacer(modifier = Modifier.size(10.dp))

        NormalTextComponent(textvalue = ((article.description ?: AppConstants.IPSUM)))

        Spacer(modifier = Modifier.weight(1f))

        AuthorDetailsComponent(article.author, article.source?.name)
    }
}

@Preview
@Composable
fun NewsRowComponentPreview() {
    val article = Articles(
        author = "Mr x",
        title = "Hello Dummy news Article",
        null,
        null,
        null,
        null,
        null,
        null
    )
    NewsRowComponent(0, article)
}

@Composable
fun AuthorDetailsComponent(authorName: String?, sourceName:String?) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp)
    ){

        authorName?.let {
            Text(it)
        }

        Spacer(modifier = Modifier.weight(1f))

        sourceName?.let {
            Text(it)
        }
    }
}

