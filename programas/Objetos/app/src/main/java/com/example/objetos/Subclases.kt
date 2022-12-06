package com.example.objetos

class Subclases {
    private var name = "padre"
    fun metodo():Unit{
        println("el nombre es $name");
    }
    inner class Dentro{
        fun metodo():Unit{
            println("el nombre es $name");
        }
    }
}