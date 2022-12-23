package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.squareup.picasso.Picasso

class Views : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views)
        //referenciamos contenedor de imagen
        var viImagen = findViewById<ImageView>(R.id.viImagen)

        //cargamos la imagen con picasso
        var imagenPrueba: String= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZkYPFEl7Y97YqxyoUz38qLTu0O504apDgeg&usqp=CAU"
        //Descargamos imagen y la añadimos a la vista
        Picasso.get().load(imagenPrueba).into(viImagen)
        // referenciamos el navegador
        var wvNavegador =findViewById<WebView>(R.id.wvNavegador)
        //activamos js en el navegador
        var webSettings= wvNavegador.getSettings()
        webSettings.javaScriptEnabled = true
        wvNavegador.setWebViewClient(WebViewClient())
        //cargamos una url
        wvNavegador.loadUrl("http://www.google.com")
    }
}