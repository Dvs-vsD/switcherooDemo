package com.example.switcheroodemo.ui.pages.NewsPage.news

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.switcheroodemo.data.RetrofitClient
import com.example.switcheroodemo.ui.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsListViewModel: ViewModel() {

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> = _news.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isFailed = MutableStateFlow(false)
    val isFailed: StateFlow<Boolean> = _isFailed.asStateFlow()

    private var currentPage: Int = 1

    fun fetchNews(pageSize: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isFailed.value = false
                getUKUtilityNews(pageSize, currentPage)
                    .onSuccess { articles ->
                        _news.value += articles
                        currentPage++
                        Log.d("News Response", articles.size.toString())
                    }
                    .onFailure {
                        //Do not show new section
                        _isFailed.value = true
                        Log.d("News Response", "Something went wrong")
                    }
                _isLoading.value = false
            } catch (e: Exception) {
                // Handle error
                _isFailed.value = true
                Log.d("News Response", e.message.toString())
            }
        }
    }

    private suspend fun getUKUtilityNews(pageSize: Int, currentPage: Int): Result<List<Article>> = try {

        //utilities OR energy OR water OR gas OR electricity
        val response = RetrofitClient.getClient().getUtilityNews(
            query = "utilities OR energy OR water OR gas OR electricity OR solar OR insurance OR broadband OR iphone OR technology",
            language = "en",
            page = currentPage,
            pageSize = pageSize,
            sortBy = "publishedAt",
            apiKey = "69f6a9be31444e6f83e87cf28bc53366"
        )
        Result.success(response.articles)
    } catch (e: Exception) {
        Result.failure(e)
    }
}