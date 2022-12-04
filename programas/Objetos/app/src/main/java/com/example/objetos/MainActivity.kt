package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var persona1: Person = Person("gerson", "34343MSG3323");
        println(persona1.alive);
        println(persona1.name);
        println(persona1.passport);
        persona1.die();

    }
}