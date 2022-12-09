package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subaclase:Subclases = Subclases()
        subaclase.metodo()

        val dentroSubClase: Subclases.Dentro = Subclases().Dentro()
        dentroSubClase.metodo()

        // implementamos la data class en nuestro proyecto
        val centauro: Star = Star("centauro", 400f, "andromeda")
        val centauroHija: Star =centauro.copy(nombre="centauro hija")

        val stars = object{
            fun fuerzaAtraccion(distance: Float, sizeOne: Float, sizeTwo: Float): Float{
                return (sizeOne*sizeTwo)/distance
            }
        }
        var estrellasAtracion=stars.fuerzaAtraccion(4000f, centauro.radius, centauroHija.radius)
        println(estrellasAtracion)
        println(centauro)
        println(centauroHija)

        var hoy:Dias=Dias.MIERCOLES
        var dias:Array<Dias> = Dias.values();
        println(hoy.name)
        println(hoy.ordinal)
        println(hoy.saludo())

        var rojo:Colores=Colores.ROJO
        println(rojo)
        println(rojo.demasValores())
    }

}

