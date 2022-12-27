package com.example.objetos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.objetos.databinding.ActivityViewsBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.*

class Views : AppCompatActivity() {
    private var contextView: Context = this
    private lateinit var binding: ActivityViewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


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
        var tvCalendario = binding.tvCalendario
        var cvCalendario =findViewById<CalendarView>(R.id.cvCalendario).run {
            //evento de cambio de dia
            setOnDateChangeListener { view, year, month, dayOfMonth ->
                binding.tvCalendario.setText("$dayOfMonth/${month+1}/$year")
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


        var sbSeekBar = findViewById<SeekBar>(R.id.seekBar2)
        sbSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Toast.makeText(contextView, "Barra movida por el usuario", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(
                    contextView,
                    "Se pulso el puntero por el usuario",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(
                    contextView,
                    "Se detuvo la interaccion del puntero por el usuario",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        sbSeekBar.max = 300
        GlobalScope.launch {
            //  progressManager(pbhCarga)
            seekManager(sbSeekBar)
        }
        var tvRating = findViewById<TextView>(R.id.tvRating)
        var ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            tvRating.text = "${ratingBar.rating}/${ratingBar.numStars}"
        }


        var textoBusqueda = "Gerson,Visoso,Ocampo,Antonio,Castro,Linda,Catalan,David,Mauricio"
        var nombres: Array<String> = textoBusqueda.split(",").toTypedArray()

        var adapterNombres: ArrayAdapter<String> =
            ArrayAdapter(contextView, android.R.layout.simple_list_item_1, nombres)

        var lvBusqueda = findViewById<ListView>(R.id.lvBusqueda).run {
            adapter = adapterNombres
            this
        }
        var svBusqueda = findViewById<SearchView>(R.id.svBusqueda)

        svBusqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                svBusqueda.clearFocus()
                if(nombres.contains(query))adapterNombres.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterNombres.filter.filter(newText)
                return false
            }
        })
        var numberPicker =findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.minValue =0
        numberPicker.maxValue =60
        numberPicker.value =0

        numberPicker.setFormatter { i-> String.format("%02d", i) }
        var tvNumberPicker =findViewById<TextView>(R.id.tvNumberPicker)
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            tvNumberPicker.text =String.format("Valor anterior: $oldVal Valor nuevo: $newVal")
        }



    }

    fun seekManager(seek: SeekBar) {
        while (true) {
            sleep(500)
            seek.incrementProgressBy(1)
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