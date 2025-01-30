package com.example.switcheroodemo.ui.pages.homepage.componants

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.switcheroodemo.MainActivity
import com.example.switcheroodemo.ui.model.BestOfferModel
import com.example.switcheroodemo.ui.pages.homepage.WebPageActivity
import com.example.switcheroodemo.ui.theme.SwitcherooDemoTheme
import com.example.switcheroodemo.utils.Constants

@Composable
fun BestOffersList(modifier: Modifier = Modifier, context: Context) {
    val offers = BestOfferModel().getBestOffers()
    LazyRow(
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp)
    ) {
        items(offers) { offer: BestOfferModel.BestOffer ->
            BestOffersItem(Modifier, offer, context = context)
        }
    }
}

@Composable
fun BestOffersItem(modifier: Modifier, offer: BestOfferModel.BestOffer, context: Context) {
    ElevatedCard(
        modifier = modifier
            .width(270.dp)
            .height(150.dp)
            .padding(end = 16.dp)
            .clickable {
                val intent = Intent(context, WebPageActivity::class.java)
                intent.putExtra(Constants.INTENT_URL, offer.url)
                intent.putExtra(Constants.INTENT_TITLE, offer.subTitle)
                context.startActivity(intent)
            }
    ) {
        Box() {
            Image(
                painter = painterResource(offer.image),
                contentDescription = "Offer",
                contentScale = ContentScale.Crop
            )

            if (offer.title.isNotEmpty()) {
                Text(
                    text = offer.title,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier.padding(8.dp),
                    color = Color.White
                )
            }



            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .height(30.dp),
                color = Color.Black.copy(alpha = 0.20f)
            ) {
                TextButton(modifier = modifier.align(Alignment.CenterEnd),
                    onClick = {},
                    content = {
                        Text(
                            text = offer.subTitle,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier,
                            color = Color.White
                        )

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow forward",
                            tint = Color.White
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, widthDp = 320)
@Composable
fun PreviewBestOffers() {
    SwitcherooDemoTheme {
        BestOffersList(Modifier, MainActivity())
    }
}