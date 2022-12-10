package com.example.objetos

class Ciclista( nombre:String,
                statura:Float,
                peso: Float,
                edad: Byte):
                Deportista(nombre, statura, peso, edad)
  {
      constructor(nombre: String,
                  statura: Float,
                  peso: Float,
                  edad: Byte,
                  tipoBicicleta: TipoBicicleta):this(nombre, statura, peso, edad){

      }
    override var velocidad: Float = 30f;
}