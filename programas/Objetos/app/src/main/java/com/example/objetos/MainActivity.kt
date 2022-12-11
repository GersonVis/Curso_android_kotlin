package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


private val Any.nombre: String?
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // println("El resultado de la resta es ${calculadora(10,5,::sumar)}")

        // objeto con funciones
        val objetoConFunciones: ObjetoConFunciones = ObjetoConFunciones(
          "probando funciones de orden superior"
        )
        objetoConFunciones.ordenSuperior(object {
            var nombre ="juan"
        }, ::mostrarObjectoPasado)
    }

}

