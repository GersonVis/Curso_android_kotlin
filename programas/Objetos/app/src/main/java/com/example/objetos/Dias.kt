package com.example.objetos

enum class Dias {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
    fun saludo(): String{
        // en un when dentro de una función se nos hobliga
        // a contemplar todos los casos de retorno
        return when(this){
            LUNES->return "buenos días"
            MARTES-> return "ay es martes"
            MIERCOLES->return "Solo dos dias mas"
            JUEVES->return "ya casi viernes"
            VIERNES->return "la mejor parte de mi vida"
            SABADO->return "fiesta"
            else->"otro día más"
        }
    }
}
enum class Colores(val hex: Int, val rgb:String){
    ROJO(0x32332, "rgb(233,233,11)"),
    BLANCO( 0xfffff,"rgb(255,255,255)");
    fun demasValores(): Colores{
        return BLANCO;
    }
}
