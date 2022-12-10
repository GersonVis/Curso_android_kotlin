package com.example.objetos

class Nadador(nombre:String,
              statura:Float,
              peso: Float,
              edad: Byte,
              private var nadoEstilo: NadoEstilo
              ): Deportista(nombre, statura, peso, edad),
               TipoEstilo{
         override var estilo:Estilo = nadoEstilo
}