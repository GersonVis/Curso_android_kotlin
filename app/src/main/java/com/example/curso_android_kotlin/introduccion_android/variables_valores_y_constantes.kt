package com.example.curso_android_kotlin.introduccion_android

import android.os.Bundle
import com.example.curso_android_kotlin.R


class variables_valores_y_constantes {
    companion object {// hace que sea accesible desde cualquier parte de la aplicación
        // no se pueden crear dentró del proceso de creación de un elemento
        // por eso se crena dentró de otro ambito
        const val mon="EUR";// las variables constantes no son aplicables a variables locales

    }
    fun onCreate(savedInstanceState: Bundle?) {
        // Kotlin e sun lenguaje compilado, se hace una reserva
        // de memoría del dato que estamos guardando
        // las variables son referencias a zonas de memoría


        // Variables: es una zona de memoría donde se almacena un dato
        // Tipos de variables
        // val: es un dato que no va a cambiarse
        val fecha_nacimiento="10 de Enero 1991";
        // var: tipo de variabel que si puede cambiar su contenido
        var nombre="juan";// dato variable que se puede cambia
        // constantes, no cambia su contenido, no pueden agregar a variables locales

        nombre="Carlos";
       // fecha_nacimiento="nuevo dato";//error no se pueden asignar nuevos valores a tipos de dato val
        println(nombre);






    }
}