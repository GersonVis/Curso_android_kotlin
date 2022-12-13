package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


val Any.nombre: String?
    get() {return this.toString()}

typealias rc = Person
typealias aliassumar = (a: Int)-> Unit
typealias mapIntStr = MutableMap<Int, String>
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
    fun calculadora(a: Int, b:Int, operacion:(a:Int, b: Int)->Int): Int{
        return operacion(a,b)
    }
    fun sumar(a: Int, b: Int): Int{
       return a+b
    }
    // funciones sin especificar el tipo de dato
    fun restar(a: Int, b: Int) = a-b
    fun multiplicar(a: Int, b: Int) = a * b

    fun mostrarObjectoPasado(parametros: Any){
            println("estamos pasando informacion")
            try {
                
                println(parametros.nombre)
            }catch (e: java.lang.Error){

            }

    }
    fun value_try(v1: Int, v2: Int):Any{
        return try{
            v1/v2
        }catch (e: Exception){
            "no se puede realizar la operacion"
        }finally {
            println("me ejecute soy un finally")
            "retorno finally"
        }
    }
    fun ordenSuperior(nombres: Array<String>, lambda:(nombres: Array<String>)->Unit):Unit{
            lambda(nombres)
    }
    fun recorrerNumeros(lambda:(numero: Int)->Int){
        for(t in 0..10){
            lambda(t)
        }
    }
    fun ordenSuperiorLambdaExterna(lambdaExterna: (dato: Int)->Unit){
        for(t in 0..10){
            lambdaExterna(t)
        }
    }
    fun retornarDesestructuraion(): Pair<String, String> {
        return "desestrurado1" to "desestructurado2"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      // usos de excepsiones
        var nula: String ?= ""
        nula=null
        var nombre: String = if (nula == null) "verdadero" else "falso"
        println("el nombre es $nombre")

        try {
            println("division por cero 0/10 ${5/0}")
        }catch (error: java.lang.ArithmeticException){
            println("Estas dividiendo entre 0")
        }finally {
            println("este mensaje siempre se ejecuta")
        }
        var resultado=value_try(0,20)
        println(" el resultadod de dividir 0/20 $resultado")
        println("el resultadod de dividir 20/00 ${value_try(20,0)}")
    }

}




