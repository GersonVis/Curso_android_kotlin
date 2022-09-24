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

        //BUCLE DO WHILE
        /*
        El bucle do while se ejecuta una vez si es verdadero continua ejecutandose
        */
        // cuando una aplicación entra en un bucle infinito android lo detecta y fuerza un cierre
        var intentos:Int=1
        var pin:Int=12345
        var pin_introducido=12344
        do{
            // encerrar entre llaves sicnifica una expresión propia
            println("número de intentos: $intentos restantes ${3-intentos}")
            if(pin==pin_introducido++){
                println("pin introducido correctamente")
                break//provoca una salida del bucle
            }

        }while(++intentos<=3)// solo se ejecutara una vez



    }
    // for
    fun recorrer_array(array: Array<String>){
        for(valor in array){
            println(valor)
        }
        println("")
        for(pos in array.indices){
            println(array.get(pos))
        }
        println("")
        for(pos in 0..array.size-1){
            println("${pos+1} ${array.get(pos)}")
        }
    }
}