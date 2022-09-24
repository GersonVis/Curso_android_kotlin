package com.example.curso_android_kotlin.introduccion_android

class Arrays_o_arreglos {
    fun onCreate(){

        //ARRAYS O ARREGLOS
        /*
            Es un conjunto de datos manejados por el mismo nombre
            Estructura:
            tipo de array(val, var, const var) nombre_array:Array<tipo de array>=arrayOf(lista de elementos)
             */
        var recibos: Array<String> =arrayOf("luz", "agua", "telefono")
        //MATRICES
        /*
        Que exista un array dentro de un array
        tipo de array(val, var, const var) nombre_array:Array<tipo de array>=arrayOf(tipo de array(val, var, const var) nombre_array:Array<tipo de array>=arrayOf(lista de elementos), tipo de array(val, var, const var) nombre_array:Array<tipo de array>=arrayOf(lista de elementos))
         */
        //var matriz: Array<Array<String>> = arrayOf(stringArray("asas"))
        var matriz2=Array<String>(5, {"$it"})
        for(valor in matriz2){
            println(valor)
        }
        var matriz3=Array(5, {it->it*4})
        for(valor in matriz3){
            println(valor)
        }
        var array_b=Array(5){i->(i*i).toString()}
        array_b.forEach{
            print("valor: $it ")
        }
        var matriz_c=Array(5, {
            Array(4, {it})
        })
        println(" arrays ")
        matriz_c.forEach{
            println("renglon\n")
            it.forEach{
                print("valor: $it ")
            }
        }
    }
}