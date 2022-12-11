package com.example.objetos

class ObjetoConFunciones (var nombre: String){
    fun ordenSuperior(valores: Any, funcionPasada: (valores: Any)->Unit){
        funcionPasada(valores)
    }
}