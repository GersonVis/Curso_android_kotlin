package com.example.objetos

enum class TipoBicicleta(var precio: Double){
    NORMAL(10.3), TODOTERRENO(300.3), DEPORTIVA(400.3);
    fun descripcion(): String{
        return when(this){
            DEPORTIVA -> "Bicicleta apta para competencias"
            TODOTERRENO -> "Bicicleta de uso rudo"
            NORMAL->return "Bicicleta de uso común"
            else-> return "Tipo de bicicleta no encontrado"
        }
    }
}