package com.example.objetos

class Car(var nombre: String): Movimiento, Sonido {
    var id: String = "0000000"
    init {
        id="cambiado"
    }
    constructor(nombre: String, id: String,):this(nombre){
       this.id=id
    }
    override var velocidad: Int = 300
       get() {return field}
       set(value) {field=value}
    override var claxon: String
        get() = TODO("Not yet implemented")
        set(value) {}

}
interface Movimiento{
    abstract var velocidad: Int;
}
interface Sonido{
    abstract var claxon: String;
    fun metodoRegular():Unit{
        println("Un método regular es aquel que tiene una" +
                "implementacion")
    }
}