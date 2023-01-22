package com.example.caballo




import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.addresselement.AddressDetails
import com.stripe.android.paymentsheet.addresselement.AddressLauncher
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOError
import java.io.IOException
import java.security.KeyStore.TrustedCertificateEntry

class CheckoutActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CheckoutActivity"
        private const val BACKEND_URL = "http://192.168.43.55:4242"
    }

    private lateinit var paymentIntentClientSecret: String
    private lateinit var paymentSheet: PaymentSheet

    private lateinit var payButton: Button

    private lateinit var addressLauncher: AddressLauncher

    private var shippingDetails: AddressDetails? = null

    private lateinit var addressButton: Button

   /* private val addressConfiguration = AddressLauncher.Configuration(
        additionalFields: AddressLauncher.AdditionalFieldsConfiguration(
            phone: AdditionalFieldsConfiguration.FieldConfiguration.Required
    ),
    allowedCountries: setOf(“US”, “CA”, “GB”),
    title: “Shipping Address”,
    googlePlacesApiKey = “(optional) YOUR KEY HERE”
    )*/
    private var level: Int? =1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val bundle = intent.extras
        level=bundle?.getInt("Level")
        if(level==null)level=1
        // Hook up the pay button
        payButton = findViewById(R.id.pay_button)
        payButton.setOnClickListener(::onPayClicked)
        payButton.isEnabled = false

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        // Hook up the address button
        addressButton = findViewById(R.id.address_button)
      //  addressButton.setOnClickListener(::onAddressClicked)

      //  addressLauncher = AddressLauncher(this, ::onAddressLauncherResult)

        fetchPaymentIntent()
      /*  var buSolicitar = findViewById<Button>(R.id.bu_solicitar)
        var etUrl=findViewById<EditText>(R.id.et_url)

        var url:String= etUrl.text.toString()
        buSolicitar.setOnClickListener {
            Toast.makeText(this, "pulsado", Toast.LENGTH_SHORT).show()
            pagar()
        }*/
    }
    private fun pagar():Unit{
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_wk6O7Cc5k3McBIG2Hut2irGs"
        )
    }
    private fun peticionPrueba(url:String): Unit{
        println("Se realizo la petición $url")
        val request = Request.Builder()
            .url(url)
            .build()
        try {

            OkHttpClient().newCall(request)
                .enqueue(object: Callback{
                    override fun onFailure(call: Call, e: IOException) {
                       showAlert("Error consulta", "Error: $e")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        showAlert("Correcto", "se realizo correctamente")

                        val responseData = response.body?.string()
                        println("Contenido: $responseData")
                        Log.i("TAG", "$responseData")
                    }

                })
        }catch (e: Error){
            println("ocurrio un error en la solicitud")
        }finally {
            println("Se hizo el intento")
        }

    }
    private fun fetchPaymentIntent() {
        val url = "$BACKEND_URL/create-payment-intent"

        val amount = 200.0f
        val payMap: MutableMap<String, Any> =HashMap()
        val itemMap: MutableMap<String, Any> =HashMap()
        val itemList: MutableList<MutableMap<String, Any>> = ArrayList()

        payMap.put("currency", "usd")
        itemMap.put("id", "photo-suscription")
        itemMap.put("amount", amount)

        itemList.add(itemMap)
        payMap.put("items", itemList)

        val shopingCardContent = Gson().toJson(payMap)

        val mediaType = "application/json; charset=utf-8".toMediaType()

        val body = shopingCardContent.toRequestBody(mediaType)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient()
            .newCall(request)
            .enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    showAlert("Failed to load data", "Error: $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        showAlert("Failed to load page", "Error: $response")
                    } else {
                        val responseData = response.body?.string()
                        val responseJson = responseData?.let { JSONObject(it) } ?: JSONObject()
                        paymentIntentClientSecret = responseJson.getString("clientSecret")
                        runOnUiThread { payButton.isEnabled = true }
                        Log.i(TAG, "Retrieved PaymentIntent")
                    }
                }
            })
    }

    private fun showAlert(title: String, message: String? = null) {
        runOnUiThread {
            val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this,  message, Toast.LENGTH_LONG).show()
        }
    }

    private fun onPayClicked(view: View) {
        val configuration = PaymentSheet.Configuration("Example, Inc.")

        // Present Payment Sheet
        paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
    }

  /*  private fun onAddressClicked(view: View) {
        addressLauncher.present(
            publishableKey = publishableKey,
            configuration = addressConfiguration
        )
    }*/

    private fun onPaymentSheetResult(paymentResult: PaymentSheetResult) {
        when (paymentResult) {
            is PaymentSheetResult.Completed -> {
               // showToast("Payment complete!")
                becamePremiun()
            }
            is PaymentSheetResult.Canceled -> {
                Log.i(TAG, "Payment canceled!")
            }
            is PaymentSheetResult.Failed -> {
                showAlert("Payment failed", paymentResult.error.localizedMessage)
            }
        }
    }
    private fun becamePremiun():Unit{
        var sharedPreferences: SharedPreferences
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.apply{
            putBoolean("PREMIUN", true)
            putInt("Level", level!!)
        }.apply()
    }

 /*   private fun onAddressLauncherResult(result: AddressLauncherResult) {
        // TODO: Handle result and update your UI
        when (result) {
            AddressLauncherResult.Success -> {
                shippingDetails = result.address
            }
            AddressLauncherResult.Canceled -> {
                // TODO: Handle cancel
            }
        }
    }*/
}