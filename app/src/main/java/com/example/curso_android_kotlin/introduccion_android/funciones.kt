package com.example.curso_android_kotlin.introduccion_android

class funciones {

    // programación modular
    /*
    * Hay veces en las que se utilizan mismas porciones de código en diferentes partes
    * es ahí donde entrá el uso de funciones
    *
    * */
    //estas variable están en ambito global
    var saldo: Float = 1000f //float utiliza 32bits tambien short
    var moneda: String = "EUR"
    var sueldo: Int = 200
    fun onCreate() {
    }

    // partes de una funcion
    fun nombre_funcion() {//parametros
        //ambito dentró de una función

    }

    //ejemplo de función
    fun mostrar_saldo() {
        println("tu saldo es $saldo $moneda")
    }
    // errores en tiempo de ejecución, son errores que solo se notan cuando se corre el programa

    fun ingresar_sueldo() {
        saldo += sueldo
    }
    // variables con parametros
    fun ingresar_dinero(ingreso: Float){
        saldo+=ingreso
        println("ingreso: $ingreso Saldo: $saldo")
    }
    fun retirar_dinero(retiro: Float){
        if(verificar_cantidad(retiro)){
            saldo-=retiro
            println("Retiro: $retiro Saldo: $saldo")
        }else{
            println("No cuentas con suficiente saldo para realizar el retiro")
        }

    }
    //funciones con retorno
    fun verificar_cantidad(cantidad: Float): Boolean//especificar el tipo de dato a retornar
    {
        return saldo>=cantidad
    }
}