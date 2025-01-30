package com.example.switcheroodemo.ui.pages.NewsPage.news

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.switcheroodemo.MainActivity
import com.example.switcheroodemo.ui.model.Article
import com.example.switcheroodemo.ui.pages.homepage.WebPageActivity
import com.example.switcheroodemo.ui.theme.SwitcherooDemoTheme
import com.example.switcheroodemo.utils.Constants


@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = NewsListViewModel(),
    pageLimit: Int,
    pagination: Boolean = false,
    context: Context
) {
    val news by remember { viewModel.news }.collectAsState()
    val isLoading by remember { viewModel.isLoading }.collectAsState()
    val isFailed by remember { viewModel.isFailed }.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        if (pagination) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo }
                .collect { visibleItems ->
                    val lastIndex = visibleItems.lastOrNull()?.index ?: 0
                    Log.d("Pagination last index", lastIndex.toString())
                    if (lastIndex >= news.size - 1 && !isLoading && !isFailed) {
                        Log.d("Pagination", "called")
                        viewModel.fetchNews(pageLimit)
                    }
                }
        } else {
            viewModel.fetchNews(pageLimit)
        }
    }

    when {
        isLoading && !pagination -> {
            Loading()
        }

        (news.isEmpty() && !pagination) || isFailed -> {
            EmptyList()
        }

        else -> {
            if (!pagination) {
                news.listIterator().forEach { article ->
                    NewsCard(modifier, article, context = context)
                }
            } else {
                LazyColumn(modifier.padding(start = 16.dp, end = 16.dp), state = listState) {
                    items(news) { article: Article ->
                        NewsCard(modifier, article, context = context)
                    }

                    if (isLoading) {
                        item {
                            Loading()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyList() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No News Available!",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun NewsCard(modifier: Modifier, article: Article, context: Context) {
    ElevatedCard(Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
        .clickable{
            val intent = Intent(context, WebPageActivity::class.java)
            intent.putExtra(Constants.INTENT_URL,article.url)
            intent.putExtra(Constants.INTENT_TITLE, "News Article")
            context.startActivity(intent)
        }) {
        Column {
            Row {
                if (!article.urlToImage.isNullOrEmpty()) {
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = "News Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                Column(Modifier.padding(8.dp)) {
                    Text(
                        text = article.title,
                        maxLines = 2,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(Modifier.size(2.dp))

                    Text(
                        text = article.description ?: "",
                        maxLines = 3,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Light,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
                Text(
                    text = article.publishedAt.take(10),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            }
        }
    }

}


@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, widthDp = 320)
@Composable
fun PreviewBestOffers() {
    SwitcherooDemoTheme {
        NewsList(Modifier, pageLimit = 5, context = MainActivity())
    }
}