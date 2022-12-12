package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


val Any.nombre: String?
    get() {return this.toString()}

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
    fun ordenSuperior(nombres: Array<String>, lambda:(nombres: Array<String>)->Unit):Unit{
            lambda(nombres)
    }
    fun recorrerNumeros(lambda:(numero: Int)->Int){
        for(t in 0..10){
            lambda(t)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // println("El resultado de la resta es ${calculadora(10,5,::sumar)}")

        // objeto con funciones
        ordenSuperior(arrayOf("gerson", "visoso", "ocampo")) { datos: Array<String> ->
            for (nombre in datos) {
                println(nombre)
            }
        }
        var mostrarNombres={ datos: Array<String> ->
            for (nombre in datos){
                println(nombre)
            }
        }
        var nombres = Array<String>(4){"nuevo nombre $it"};
        ordenSuperior(nombres, mostrarNombres)

        var lambdaRetorno ={x:Int ->"estoy retornando un valor $x"}
        var retornado = lambdaRetorno(0)

        println(retornado)

        var numeros = IntArray(5){it}

        recorrerNumeros(){
            it
        }
        recorrerNumeros { x: Int -> x }

    }

}

