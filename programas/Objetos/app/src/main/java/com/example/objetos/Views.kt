package com.example.objetos

import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.*

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

        //codigo de video
        var videoView =findViewById<VideoView>(R.id.videoView)
        //creamos el controlador del video
        var controller =MediaController(this)

        //unimos la vista al controlador
      //  controller.setAnchorView(videoView)
        //agregamos el controller a la view
      //  videoView.setMediaController(controller)

        //cargar video con URI
        var videoLocal = "android.resource://"+packageName+"/"+R.raw.video
        //println(packageName)
        videoView.setVideoURI(Uri.parse(videoLocal))
     //   videoView.start()
        var tvCalendario =findViewById<TextView>(R.id.tvCalendario)
        var cvCalendario =findViewById<CalendarView>(R.id.cvCalendario).run {
            //evento de cambio de dia
            setOnDateChangeListener { view, year, month, dayOfMonth ->
                tvCalendario.setText("$dayOfMonth/${month+1}/$year")
            }
            this
        }

        //establecer una fecha en el calendario
        var dayAfterTomorrow = Calendar.getInstance()
        dayAfterTomorrow.set(2022, 11, 25)
        cvCalendario.date = dayAfterTomorrow.timeInMillis

        //hacer que el calendario en otro día en vez de domingo
        var d = cvCalendario.firstDayOfWeek
        cvCalendario.firstDayOfWeek = (d + 1) % 7


        var pbhCarga = findViewById<ProgressBar>(R.id.progressBarHorinzontalDos)
        GlobalScope.launch {
            progressManager(pbhCarga)
        }
    }


    fun progressManager(pb: ProgressBar) {
        while (pb.progress < pb.max) {
            sleep(1000)
            pb.incrementProgressBy(5)
            pb.incrementSecondaryProgressBy(10)
        }
    }
}