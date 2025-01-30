package com.example.switcheroodemo.ui.pages.homepage.componants

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.switcheroodemo.MainActivity
import com.example.switcheroodemo.ui.model.Services
import com.example.switcheroodemo.ui.pages.homepage.Services
import com.example.switcheroodemo.ui.pages.homepage.WebPageActivity
import com.example.switcheroodemo.ui.theme.SwitcherooDemoTheme
import com.example.switcheroodemo.utils.Constants

@Composable
fun HorizontalGridServices(modifier: Modifier, context: Context) {
    val services = Services().getServices()
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .height(300.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(services) { service: Services.ServiceModel ->
            ServiceItem(service, context = context)
        }
    }
}

@Composable
fun ServiceItem(service: Services.ServiceModel, context: Context) {
    Card(modifier = Modifier
        .width(100.dp)
        .height(50.dp).padding(2.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            val intent = Intent(context, WebPageActivity::class.java)
            intent.putExtra(Constants.INTENT_URL, service.url)
            intent.putExtra(Constants.INTENT_TITLE, service.name)
            context.startActivity(intent)
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize(), // This will make the Box fill the Card
            contentAlignment = Alignment.Center // This will center the content inside the Box
        ) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .clip(CircleShape)
                        .wrapContentSize(),
                    color = colorResource(service.color).copy(alpha = 0.1f)
                ) {
                    Icon(
                        painter = painterResource(service.icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(56.dp)
                            .padding(16.dp),
                        tint = colorResource(service.color)
                    )
                }

                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = service.name,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, widthDp = 320)
@Composable
fun HorizontalGridPreview() {
    SwitcherooDemoTheme {
        HorizontalGridServices(modifier = Modifier, MainActivity())
    }
}
