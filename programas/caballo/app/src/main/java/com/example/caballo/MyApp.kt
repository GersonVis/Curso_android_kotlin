package com.example.caballo



import android.app.Application
import com.stripe.android.PaymentConfiguration

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_wk6O7Cc5k3McBIG2Hut2irGs"
        )
    }
}