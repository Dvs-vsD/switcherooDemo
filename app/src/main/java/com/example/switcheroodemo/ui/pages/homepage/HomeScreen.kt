package com.example.switcheroodemo.ui.pages.homepage

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.switcheroodemo.MainActivity
import com.example.switcheroodemo.R
import com.example.switcheroodemo.ui.pages.NewsPage.NewsActivity
import com.example.switcheroodemo.ui.pages.homepage.componants.BestOffersList
import com.example.switcheroodemo.ui.pages.homepage.componants.HorizontalGridServices
import com.example.switcheroodemo.ui.pages.NewsPage.news.NewsList
import com.example.switcheroodemo.ui.theme.SwitcherooDemoTheme

@Composable
fun HomeScreen(modifier: Modifier, context: Context) {
    Column(
        modifier = modifier
    ) {
        Greeting("Danny")

        Services(
            Modifier.padding(16.dp), context
        )
    }

}

@Composable
fun Greeting(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Good Morning, \n$name! 👋",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),

                ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceDim
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "",
                        modifier = Modifier.padding(8.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),

                ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Image(
                        painter = painterResource(R.drawable.avtar),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, widthDp = 320)
@Composable
fun GreetingPreview() {
    SwitcherooDemoTheme {
        Greeting("Android")
    }
}

@Composable
fun Services(modifier: Modifier, context: Context) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(rememberNestedScrollInteropConnection()),
    ) {
        item {
            Text(
                text = "Are you spending too much on utilities? 💵\uD83D\uDCB5 \uD83D\uDCB5",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Text(
                text = "See How much you could save",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            HorizontalGridServices(modifier, context)
        }

        item {
            Text(
                text = "Today's best offers!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            BestOffersList(context = context)
        }

        item {
            Row(Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Latest News & Articles",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                TextButton(
                    onClick = {
                        val intent = Intent(context, NewsActivity::class.java)
                        context.startActivity(intent)
                    },
                    content = {
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black.copy(alpha = 0.4f)
                        )

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow forward",
                            tint = Color.Black.copy(alpha = 0.4f)
                        )
                    }
                )
            }

        }

        item {
            NewsList(modifier, pageLimit = 3, context = context)
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, widthDp = 320)
@Composable
fun PreviewServices() {
    SwitcherooDemoTheme {
        Services(Modifier, context = MainActivity())
    }
}