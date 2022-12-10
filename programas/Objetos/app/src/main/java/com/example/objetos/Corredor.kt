package com.example.objetos

class Corredor(nombre:String,
               statura:Float,
               peso: Float,
               edad: Byte,
               private var corredorEstilo: CorredorEstilo
                ): Deportista(nombre, statura, peso, edad),
                TipoEstilo {
    override var estilo: Estilo = corredorEstilo
}
