package com.example.objetos

import android.annotation.SuppressLint


//constructor son la parte inicial de una clase, con los cuales
//nosotros queremos que se inicialice el objeto
class Person(var name: String ="sin name", var passport: String? = null) {
    var alive: Boolean = true;
    //metodo cambio de variable de estado de alive

    fun die(){
        alive=false;
    }

}