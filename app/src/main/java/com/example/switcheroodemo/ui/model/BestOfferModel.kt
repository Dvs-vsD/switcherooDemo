package com.example.switcheroodemo.ui.model

import android.graphics.drawable.Drawable
import android.net.Uri
import com.example.switcheroodemo.R

class BestOfferModel {
    data class BestOffer(
        val image: Int, val title: String, val subTitle: String, val url: String
    )

    fun getBestOffers(): List<BestOffer> {
        val list = ArrayList<BestOffer>();
        list.add(
            BestOffer(
                R.drawable.offer_1,
                "Don't Pay More for High Speed Broadband",
                "Compare Broadband",
                "https://switcheroo.co.uk/compare/broadband/"
            )
        )

        list.add(
            BestOffer(
                R.drawable.offer_2,
                "",
                "Trade In Mobile",
                "https://mywebconect.com/?a=1754&c=8032&s1="
            )
        )

        list.add(
            BestOffer(
                R.drawable.offer_3,
                "Save up to £504* when You Switch Car Insurance",
                "Compare Car Insurance",
                "https://switcheroo.co.uk/car-insurance/"
            )
        )

        list.add(
            BestOffer(
                R.drawable.offer_4,
                "Find The Best Energy Tariff and Supplier For Your Area",
                "Compare Energy",
                "https://switcheroo.co.uk/compare/energy-uk-s2?source=Homepage"
            )
        )

        list.add(
            BestOffer(
                R.drawable.offer_5,
                "15% off Boiler Insurance\n" +
                        "Use Code ‘GET15’ | Free Boiler Service",
                "View Offer",
                "https://mywebconect.com/?a=1754&c=7990&p=r&s1="
            )
        )

        return list
    }
}