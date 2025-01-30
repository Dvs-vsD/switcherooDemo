package com.example.switcheroodemo.ui.model

import androidx.annotation.ColorRes
import androidx.compose.material.icons.Icons
import com.example.switcheroodemo.R

class Services  {
    data class ServiceModel(
        val icon: Int,
        val name: String,
        val url: String,
        val color: Int,
    )

    fun getServices() : List<ServiceModel> {
        val services = ArrayList<ServiceModel>()
        services.add(
            ServiceModel(
                R.drawable.fire,
                "Energy",
                "https://switcheroo.co.uk/compare/energy-s2-af?source=homepage",
                R.color.orange
            )
        )

        services.add(
            ServiceModel(
                R.drawable.broadband,
                "Broadband",
                "https://switcheroo.co.uk/compare-broadband-online-deals",
                R.color.purple_500

            )
        )

        services.add(
            ServiceModel(
                R.drawable.smartphone,
                "Mobile",
                "https://switcheroo.co.uk/compare-mobile/deals/",
                R.color.black
            )
        )

        services.add(
            ServiceModel(
                R.drawable.sun,
                "Solar",
                "https://switcheroo.co.uk/compare/solar-s2-online/",
                R.color.yellow
            )
        )

        services.add(
            ServiceModel(
                R.drawable.health,
                "Health \nInsurance",
                "https://switcheroo.co.uk/health-insurance",
                R.color.green
            )
        )

        services.add(
            ServiceModel(
                R.drawable.home,
                "Home \nInsurance",
                "https://switcheroo.co.uk/home-insurance/",
                R.color.sky_blue
            )
        )

        services.add(
            ServiceModel(
                R.drawable.car,
                "Car \nInsurance",
                "https://switcheroo.co.uk/car-insurance/",
                R.color.blue
            )
        )

        services.add(
            ServiceModel(
                R.drawable.van,
                "Van \nInsurance",
                "https://switcheroo.co.uk/van-insurance/",
                R.color.purple_700
            )
        )

        services.add(
            ServiceModel(
                R.drawable.helmet,
                "Bike \nInsurance",
                "https://switcheroo.co.uk/bike-insurance",
                R.color.red
            )
        )

        services.add(
            ServiceModel(
                R.drawable.paws,
                "Pet \nInsurance",
                "https://switcheroo.co.uk/pet-insurance/",
                R.color.teal
            )
        )

        return services
    }
}
