package com.example.curso_android_kotlin.introduccion_android

class Estructuras_de_control {
    fun onCreate() {
        var vip: Boolean = false
        // se puede hacer un if sin else
        if (vip == true) println("el vip es verdadero")
        //if con else, no es obligatorio si es una line a de codigo
        if (vip) println("el vip es verdadero")
        else println("el vip es falso")

        
        // con llaves
        if (vip){
            println("el vip es verdadero")
        }
        else {
            println("el vip es falso")
        }


        when (2) {

        }
        for (t in 1..10) {

        }
        while (3 > 0) {

        }
    }
}