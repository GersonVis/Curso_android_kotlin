package com.example.curso_android_kotlin.introduccion_android

class Inline_fun {
    inline fun operacion(a: Int, b:Int, sumar: (a: Int, b: Int)->Unit, restar: (a: Int, b: Int)->Unit){
        sumar(a, b)
        restar(a, b)
    }
    fun sumar(a: Int, b:Int): Unit{
        println("el resultado de la suma es: ${a+b}")
    }
    fun restar(a: Int, b: Int){
        println("el resultado de la resta es: ${a-b}")
    }
    fun main() {
        operacion(10, 5, ::sumar, ::restar)
    }
}