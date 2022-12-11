package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //agregar un nuevo metodo a la clase String
    private fun String.nospace():String{
        //recordar que en kotlin todos son objetos hasta los tipos de datos
        // por eso mismo se puede acceder a los valores del string directamente
        return this.replace(" ", "")
    }
    private fun Array<Int>.mostrar(){
            println("{")
            for(i in this) println(i)
            println("}")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nombre:String="gerson visoso ocampo".nospace()

        var birthDays: Array<Int> = Array<Int>(10){it}
        birthDays.mostrar()
    }

}

