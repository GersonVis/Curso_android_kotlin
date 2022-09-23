package com.example.curso_android_kotlin.introduccion_android

import android.os.Bundle

class operadores_de_calculo {
    fun onCreate(savedInstanceState: Bundle?) {
        // operaciones
        var a:Int=5+5//10
        var b:Int=10-2//8
        var c:Int=3*4//12
        var d:Int=10/5//2
        var e:Int=10%3//1 el resto de dividir dos número enteros
        var f:Int=10/6 // divición infinita solo se guarda la parte entera

        // INCREMENTOS DECREMENTOS
        /*
        * se pueden hacer incrementos y decrementos antes de la variable
        * por ejemplo si x vale 1 se puede hacer un incremento antes y despues
        * antes: ++x se le asigna el nuevo valor a x
        * despues: x++ muestra x y luego le asigna el nuevo valor
        * */
        //EJEMPLO
        var preIncremento: Int=0
        var preDecremento: Int=0

        var posIncremento: Int=0
        var posDecremento: Int=0

        println(++preIncremento)//hace la operación y luego ejecuta la linea muestr: 1
        println(preIncremento)// 1
        println(--preDecremento)// hace la operación y luego ejecuta la linea muestra: -1
        println(preDecremento)// -1

        println(posIncremento++)// ejecuta la linea y luego hace la operación muestra: 0
        println(posIncremento)// 1
        println(posDecremento--)//ejecuta la linea y luego hace la operación muestra: 0
        println(posDecremento)//-1

        // cuando las operaciones son sobre la misma variable
        var saldo: Int=0
        var aumento: Int=300

        saldo+=aumento
        saldo-=aumento
        saldo/=aumento


    }
}