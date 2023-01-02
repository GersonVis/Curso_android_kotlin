package com.example.caballo

import android.graphics.Point
import android.media.Image
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import androidx.core.content.ContextCompat
import com.example.caballo.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private  var cell_selected_x: Int = 0
    private  var cell_selected_y: Int = 0
    private lateinit var board: Array<Array<Int>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resetBoard()
        setFirstPosition()
        initScreenGame()
    }
    private fun resetBoard():Unit{
        board = Array<Array<Int>>(8){Array<Int>(8){0} }
    }
    private fun setFirstPosition():Unit{
        var x: Int = 0
        var y: Int = 0
        y = (0..7).random()
        x = (0..7).random()
        //guardamos las posiciones en variables globales
        cell_selected_x=x
        cell_selected_y=y

        selectCell(x, y)
    }
    fun checkOnClickListener(v: View): Unit{
        var name:String = v.tag.toString()
        var x:Int = name.subSequence(1,1).toString().toInt()
        var y:Int = name.subSequence(2,2).toString().toInt()
        selectCell(x, y)
    }

    private fun selectCell(x: Int, y: Int){
        board[x][y]=1
        paintHorseCell(cell_selected_x, cell_selected_y, "previus_cell")
        cell_selected_x=x
        cell_selected_y=y
        paintHorseCell(x, y, "selected_cell")
    }
    private fun paintHorseCell(x: Int, y: Int, color: String){
        var iv = findViewById<ImageView>(resources.getIdentifier( "c$x$y", "id", packageName))
        iv.setBackgroundColor(ContextCompat.getColor(this, resources.getIdentifier(color, "color", packageName)))
        iv.setImageResource(R.drawable.caballo)
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
        var heigth_cell = width_cell


        for(i in 0..7){
            for(j in 0..7){

                iv = findViewById(resources.getIdentifier("c$j$i", "id", packageName))
                var width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width_cell, getResources().getDisplayMetrics()).toInt()
                var heigth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heigth_cell, getResources().getDisplayMetrics()).toInt()
                iv.setLayoutParams(TableRow.LayoutParams(width, heigth))
            }
        }




    }
}