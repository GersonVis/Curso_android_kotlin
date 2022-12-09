package com.example.objetos

data class Star(val nombre: String, val radius: Float, val galaxy: String)
{
    var alive: Boolean = true
    fun cambiar(nuevo: Boolean){
        alive=nuevo
    }
}
