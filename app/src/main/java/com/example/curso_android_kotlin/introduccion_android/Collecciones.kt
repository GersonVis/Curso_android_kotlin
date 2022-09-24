package com.example.curso_android_kotlin.introduccion_android

class Collecciones {
    fun onCreate(){
        /*
        * COLECCIONES:
        * SET
        * LISTAS
        * MAPAS O DICCIONARIOS
        *
        * TIPOS
        * mutables
        * inmutables
        * */

        // SET
        // coleccion inmutable
        var ids: Set<Int> = setOf(1223,23212,23232)
        var combinacion=setOf("nombre", 3232, true)

        //saber si existe elemento dentro del set
        if(combinacion.contains("nommbre")){
            println("si existe el dato dentro")
        }

        // coleccion set mutable
        var setmutable: MutableSet<String> = mutableSetOf("elemento1", "elemnto2", "elemento1")

        setmutable.add("nuevo dato")// esto no se puede hacer una lista set inmutable

        // elimina todas las coincidencias dentro del set
        setmutable.remove("elemento1")
        println(setmutable)

        // borrar todo
        setmutable.clear()

        // LIST


        // colecciones list y list mutable, el add no funciona, ni asignación de nuevos valores una vez declarada set
        var lista: List<Int> = List(5, {it*it})
        println(lista)
        // colecciones mutables
        var lista_mutable: MutableList<String> = MutableList(5, {"opcion $it"})
        lista_mutable[0]="OPCION MODIFICADA"
        println(lista_mutable)
        // remover un indice
        lista_mutable.removeAt(0)
        println(lista_mutable)
        // obtener el primero
        println(lista_mutable.first())
        // obtener el útimo
        println(lista_mutable.last())
        // si no existe obtener nulo
        println(lista_mutable.firstOrNull())
        // borrar lista
        lista_mutable.clear()
        // saber si esta vacía
        println(lista_mutable.none())


        // MAPAS O DICCIONARIOS
        /*
        Es una clave asociada a un valor, valores en pares
        Estructura
        tipo var, val, const val nombre: Map<tipo_de_dato_key, tipo_de_dato_contenido> = mapOf(
          identificador to valor,
          identificador2 to valor2
        )
         */
        // inmutable
        var mapa: Map<Int, String> = mapOf(
            1 to "España",
            2 to "México"
        )
        println(mapa)

        // mapa mutable
        var mapa_mutable = mutableMapOf<Int, String>(
            1 to "gerson",
            2 to "visosos"
        )
        println(mapa_mutable)
        // agregar un dato
        mapa_mutable.put(3, "Ocampo")
        println(mapa_mutable)

    }
}