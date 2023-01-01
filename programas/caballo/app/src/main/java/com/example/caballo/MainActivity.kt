package com.example.caballo

import android.graphics.Point
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TableRow
import com.example.caballo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initScreenGame()
    }
    fun initScreenGame():Unit{
            setSizeBoard()
            hideMessage()
    }
    private fun hideMessage():Unit{
        binding.lyMessage.visibility = android.view.View.INVISIBLE
    }
    private fun setSizeBoard():Unit{
        var iv: ImageView
        val display = windowManager.defaultDisplay

        val size= Point()
        display.getSize(size)

        val width=size.x
        var width_dp =  (width/ getResources().getDisplayMetrics().density)

        var lateralMargin= 0
        var width_cell = (width_dp-lateralMargin)/8
        var heigth_cell = width_dp

        for(i in 0..7){
            for(j in 0..7){
                println("c$j$i")
                iv = findViewById(resources.getIdentifier("c$j$i", "id", packageName))
              /*  var width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width_cell, getResources().getDisplayMetrics()).toInt()
                var heigth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heigth_cell, getResources().getDisplayMetrics()).toInt()
                iv.setLayoutParams(TableRow.LayoutParams(width, heigth))*/
            }
        }




    }
}