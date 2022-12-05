package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var persona1: Person = Person("gerson", "34343MSG3323");



        var pele: Atleta = Atleta("Pele", "si")

        println(pele.getResidence());
        pele.setResidence("iguala guerrero");
        println(pele.getResidence())
        pele.hablar()
    }
}

