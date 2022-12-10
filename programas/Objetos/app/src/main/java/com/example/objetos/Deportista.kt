package com.example.objetos

open class Deportista(var nombre:String,
                 var statura:Float,
                 var peso: Float,
                 var edad: Byte): Caracteristicas(){
    override var velocidad: Float = 0f;
}
