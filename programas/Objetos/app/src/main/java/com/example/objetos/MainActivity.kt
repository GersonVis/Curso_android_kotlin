package com.example.objetos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subaclase:Subclases = Subclases()
        subaclase.metodo()

        val dentroSubClase: Subclases.Dentro = Subclases().Dentro()
        dentroSubClase.metodo()
    }

}

