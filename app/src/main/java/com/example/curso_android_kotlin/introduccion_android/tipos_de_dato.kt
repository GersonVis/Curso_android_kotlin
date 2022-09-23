package com.example.curso_android_kotlin.introduccion_android

import android.os.Bundle

class tipos_de_dato {
    // variables accesibles dentró de toda la clase
    var color_carro: String= "Azul"

    fun onCreate(savedInstanceState: Bundle?) {
        // todas las variables dentro de esta función solo son accesibles dentró del metodo


        // kotlin es un lenguage tipado por que se debe especificar un tipo de dato de las variables
        // por ejemplo si le asignamos a la variable nombre un contenido string automaticamente
        // la variable pasa a ser de tipo estring, este tipo de dato en su inicialización será
        // el tipo de dato en sus futuros usos y no lo podremos cambiar si asignamos string siempre
        // tendrá que almacenar string
        var nombre="gerson"/*   asignación automatica del tipo de dato*/
        var edad: Int =5// tipo de dato declarado en la asignación
        // nos marca de amarillo por que hay una forma de hacer lo mimo pero más simple
        var fecha: String /*tercera forma de asignacion de variables
        en los string cada letra tiene una posición en la cadena esta asignación de posiciones
        comienza en la posición 0
        */

        fecha= "13 Enero 99"

        // tipos de datos existentes
        /*Orden de los datos por más memoría
            Double 64bits
            Long 64bits
            Float 32bits
            Int 32bits 4294967296
            Short 16bits
            Byte 8bits
            char 8bits -128 a 127
        * */
        /* Según el tipo de dato utilizado se hace una reserva mucho más grande de memoria
        *  Para operar entre variables deben ser del mismo tipo
        * */
        // boleano solo puede tener dos valores
        var vip: Boolean= true// solo tiene dos valores falsos y verdaderos
        var saludo="hola mi saldo es: " //tipo estriing texto encerrado en comillas
        var saldo: Float= 13.5f // tipos de dato float siempre poner al final de la cantidad una f
        var numero_turno: Int = 13 // tipo de dato entero
        var byte_prueba: Byte= 3
        //double
        var cambio: Double = 3.12 /* tipo de dato que permite tambien tener decimales, permite
         permite almacenar números mucho más grandes por lo tanto necesita más memoria*/
        var inicial: Char ='g'

        //LAS VARIABLES DEBEN SER DEL MISMO TIPO PARA OPERAR ENTRE ELLAS
        // USAR .toTipo_de_dato() para tratar la variable de un tipo de dato diferente
        // ejemplo numero.toString()
        cambio= (saldo+numero_turno).toDouble()// se hace una converción
        println(saludo+saldo)
        edad.toString()
        saludo.toInt()




    }
}