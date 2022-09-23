package com.example.curso_android_kotlin.introduccion_android

class Estructuras_de_control {
    fun onCreate() {
        var vip: Boolean = false
        // se puede hacer un if sin else
        if (vip == true) println("el vip es verdadero")
        //if con else, no es obligatorio si es una line a de codigo
        if (vip) println("el vip es verdadero")
        else println("el vip es falso")



        // USO DE WHEN
        var fecha="05/06/1990"

        // obtenemos el mes de la fecha, y convertimos en número
        var mes:Int=fecha.subSequence(3, 5).toString().toInt()
        var especifidad:Int=13
        println(mes)
        //entre parentesis la opción a comprobar
        when(mes){
            1->{
                println("Enero")
            }
            2->{
                println("Febrero")
            }
            in(3..12)->{
                println("No declarado todavía")
            }
            especifidad->{
                println("mes trece")
            }
            // para cuando no se cumple ningún caso
            else->println("no existe ese número de mes")
        }

        when(mes){
            1,2,3,4,5,6->{
                println("primer semestre")
            }
            7,8,9,10,11,12->{
                println("segundo semestre")
            }
            // para cuando no se cumple ningún caso
            else->{
                println("fuera del rango")
            }
        }

        // con llaves
        if (vip){
            println("el vip es verdadero")
        }
        else {
            println("el vip es falso")
        }



        for (t in 1..10) {

        }
        while (3 > 0) {

        }
    }
}