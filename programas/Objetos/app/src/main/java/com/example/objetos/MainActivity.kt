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

       // println("El resultado de la resta es ${calculadora(10,5,::sumar)}")
        var variableCompuesta = "variable1" to 1234
        val (variable1, variable2) = variableCompuesta
        println("variable1: $variable1 variable2: $variable2")

        var variableConMetodos = "metodo1" to "metodo2"
        var variableAlmacen1: String = variableConMetodos.component1()
        var variableAlmacen2: String = variableConMetodos.component2()

        println("variable accedida por metodo 1: $variableAlmacen1 variable " +
                "accededida por método 2: $variableAlmacen2")
        val(v1,v2)=retornarDesestructuraion()
        println("$v1, $v2")
        var (v5, v6, v7) = Star("sol", 433f, "via lactea")
        println("$v5 $v6 $v7")
    }

}



