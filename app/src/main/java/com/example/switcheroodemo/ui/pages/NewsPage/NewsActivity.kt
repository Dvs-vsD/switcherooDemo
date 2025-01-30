package com.example.switcheroodemo.ui.pages.NewsPage

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.switcheroodemo.ui.pages.NewsPage.news.NewsList
import com.example.switcheroodemo.ui.pages.NewsPage.ui.theme.SwitcherooDemoTheme

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            NewsScreen(onBackPress = { finish() }, context = this)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(onBackPress: () -> Unit, context: Context) {
    SwitcherooDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Latest News") },
                    navigationIcon = {
                        IconButton(
                            onClick = onBackPress
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back Navigation"
                            )
                        }
                    }
                )
            }) { innerPadding ->
            NewsList(
                modifier = Modifier.padding(innerPadding),
                pageLimit = 10,
                pagination = true,
                context = context
            )
        }
    }
}