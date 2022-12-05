package com.example.objetos

class Car(var nombre: String): Movimiento(), Sonido {
    var id: String = "0000000"
    init {
        id="cambiado"
    }
    constructor(id: String, nombre: String):this(nombre){

    }
    override var velocidad: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var claxon: String
        get() = TODO("Not yet implemented")
        set(value) {}

}
abstract  class Movimiento(){
    abstract var velocidad: Int;
}
interface Sonido{
    abstract var claxon: String;
    fun metodoRegular():Unit{
        println("Un método regular es aquel que tiene una" +
                "implementacion")
    }
}