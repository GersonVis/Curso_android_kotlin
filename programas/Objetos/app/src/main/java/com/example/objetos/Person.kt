package com.example.objetos

import android.annotation.SuppressLint


//constructor son la parte inicial de una clase, con los cuales
//nosotros queremos que se inicialice el objeto
open class Person(protected var name: String ="sin name", private var passport: String? = null, protected var residence: String="",
                  override var saludo: String
):saludar() {
    override fun hacerSaludo() {
        TODO("Not yet implemented")
    }
}
//las clases traen por defecto habilitado final
// que quiere decir que son la última parte de bloque
class Atleta(name: String ="sin name", passport: String? = null, residence: String=""):Person(name, passport, residence){
      private var medallas: Int=0;
      fun getMedallas(): Int{return this.medallas}
      fun setMedallas(numeroDeMedallas:Int): Unit{this.medallas=numeroDeMedallas}
      internal fun getResidence():String{
          if(this.residence==""){
              return "no hay residencia guardada para esta persona"
          }
          return this.residence;
      }
      internal fun setResidence(newResidence:String):Unit{
          this.residence=newResidence
      }
    override fun hablar():Unit{
        println("yo hablo con acento brazileño");
    }
}
abstract class saludar{
    abstract var saludo:String
    abstract fun hacerSaludo():Unit
}