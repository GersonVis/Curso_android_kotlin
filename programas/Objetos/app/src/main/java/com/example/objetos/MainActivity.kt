package com.example.objetos

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlin.random.Random


val Any.nombre: String?
    get() {return this.toString()}

typealias rc = Person
typealias aliassumar = (a: Int)-> Unit
typealias mapIntStr = MutableMap<Int, String>
class MainActivity : AppCompatActivity() {
    lateinit var perro:String

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
    var padre=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvEjemplo = findViewById<TextView>(R.id.tvEjemplo)
        tvEjemplo.apply {
            text = "texto cambiado desde codigo"
            setTextColor(Color.parseColor("#FFFF0000"))
            setTextColor(Color.RED)
            setTextColor(-65536)
            setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
            setTextSize(32f)
            setOnClickListener{
                Toast.makeText(padre, "hola", Toast.LENGTH_LONG).show()
                with(tvEjemplo) {
                    this.setTextColor(Color.GREEN)
                }
            }
        }

        var etEjemplo = findViewById<EditText>(R.id.etEjemplo)

        etEjemplo.addTextChangedListener{
            if(etEjemplo.text.length==0) etEjemplo.setError("No puede ser vacio")
        }
        etEjemplo.setSelection(0,4)
        var inicio = etEjemplo.selectionStart
        var fin= etEjemplo.selectionEnd
      //  etEjemplo.selectAll()

    }

    class Persona(msg: String): Exception(msg){

    }

    override fun onStart() {
        super.onStart()
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}




