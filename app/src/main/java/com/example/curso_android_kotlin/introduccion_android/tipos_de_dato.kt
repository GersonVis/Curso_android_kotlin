package com.example.curso_android_kotlin.introduccion_android

import android.os.Bundle

class tipos_de_dato {

    fun onCreate(savedInstanceState: Bundle?) {

        // kotlin es un lenguage tipado por que se debe especificar un tipo de dato de las variables
        // por ejemplo si le asignamos a la variable nombre un contenido string automaticamente
        // la variable pasa a ser de tipo estring, este tipo de dato en su inicialización será
        // el tipo de dato en sus futuros usos y no lo podremos cambiar si asignamos string siempre
        // tendrá que almacenar string
        var nombre="gerson"//   asignación automatica del tipo de dato
        var edad: Int =5// tipo de dato declarado en la asignación
        // nos marca de amarillo por que hay una forma de hacer lo mimo pero más simple
        var fecha: String // tercera forma de asignacion de variables

        fecha= "13 Enero 99"

        // tipos de datos existentes
        // boleano solo puede tener dos valores
        var vip: Boolean= true// solo tiene dos valores falsos y verdaderos
        var saludo="saludo" //tipo estriing texto encerrado en comillas
        var saldo: Float= 13.5f // tipos de dato float siempre poner al final de la cantidad una f
        var numero_turno: Int = 13 // tipo de dato entero
        var cambio: Double = 3.12 // tipo de dato que permite tambien tener decimales

    }
}