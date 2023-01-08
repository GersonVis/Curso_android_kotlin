package com.example.caballo

import android.graphics.Point
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.caballo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cell_selected_x: Int = 0
    private var cell_selected_y: Int = 0
    lateinit var board: Array<Array<Int>>
    private var board_moves: Array<Array<Int>> = Array<Array<Int>>(8) { Array<Int>(8) { 0 } }
    private var options: Int = 0
    private var moves: Int = 64
    private var movesRequire: Int = 4
    private var bonus: Int = 0
    private var bonusWidth: Int = 0
    private var levelMoves: Int = 20
    private var checkMovement: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resetBoard()
        setFirstPosition()
        initScreenGame()
    }

    private fun resetBoard(): Unit {
        board = Array<Array<Int>>(8) { Array<Int>(8) { 0 } }
    }

    private fun setFirstPosition(): Unit {
        var x: Int = 0
        var y: Int = 0
        y = (0..7).random()
        x = (0..7).random()
        //guardamos las posiciones en variables globales
        cell_selected_x = x
        cell_selected_y = y

        selectCell(x, y)
    }

    private fun checkedCell(x: Int, y: Int) {
        if (board[y][x] == 0 || board[y][x] == 9 || board[y][x] == 2) {
            println("dentro checkedCell")
            checkedCaballo(
                cell_selected_x,
                cell_selected_y,
                x,
                y
            )
        }
    }

    private fun checkedCaballo(x: Int, y: Int, x_touch: Int, y_touch: Int): Unit {
        if (Math.abs(x - x_touch) == 2) {
            if (Math.abs(y - y_touch) == 1) {
                selectCell(x_touch, y_touch)
                if(options==0 && !checkMovement) painAllOptions()
                return
            }
        }
        if (Math.abs(y - y_touch) == 2) {
            if (Math.abs(x - x_touch) == 1) {
                selectCell(x_touch, y_touch)
                if(options==0 && !checkMovement) painAllOptions()
                return
            }
        }
        if (!checkMovement) {
            Toast.makeText(this, "nocheck_primero", Toast.LENGTH_SHORT).show()
            selectCell(x_touch, y_touch)
            bonus--
            binding.tvBonusData.text = if (bonus != 0) bonus.toString() else ""

        }
    }
    private fun painAllOptions(){
        for(y in 0..7){
            for(x in 0..7){
                if(board[y][y]!=1 || board[y][x]==2){
                    board[y][y]=9
                    paintOption(x, y)

                }
            }
        }
    }
    fun checkOnClickListener(v: View) {
        var name: String = v.tag.toString()
        var x: Int = name.subSequence(1, 2).toString().toInt()
        var y: Int = name.subSequence(2, 3).toString().toInt()

        println("pulsado")
        checkedCell(x, y)

    }

    private fun incrementoBonus(): Unit {
        bonus++
    }

    private fun growProgressBonus(): Unit {
        var moves_done = 64 - moves

        var bonus_done = moves_done / movesRequire
        var moves_rest = movesRequire * (bonus_done)
        var bonus_grow = moves_done - moves_rest

        var widthBonus: Float = ((bonusWidth / movesRequire) * bonus_grow).toFloat()

        var heigth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            8F, getResources().getDisplayMetrics()
        ).toInt()

        var width = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            widthBonus, getResources().getDisplayMetrics()
        ).toInt()

        var v = findViewById<View>(R.id.vNewBonus)
        v.setLayoutParams(TableRow.LayoutParams(width, heigth))
    }

    private fun showCountOnBonus(x: Int, y: Int, check: Int) {
        println("el valor de la celda es ${board[y][x]} check:$check ${board[y][x] == check}")
        if (board[y][x] == check) {
            incrementoBonus()
            binding.tvBonusData.text = bonus.toString()
        }
    }

    private fun selectCell(x: Int, y: Int) {
        println("dentro selectCell")
        moves--
        showCountOnMoves(moves)
        showCountOnBonus(x, y, 2)
        board[y][x] = 1
        paintHorseCell(cell_selected_x, cell_selected_y, "previus_cell")
        cell_selected_x = x
        cell_selected_y = y
        growProgressBonus()
        clearOptions()
        checkedOption(x, y)
        paintHorseCell(x, y, "selected_cell")

        checkMovement = true
        if (moves > 0) {
            checkNewBonus()
            checkGameOver(x, y)
        } else showMessage("You win", "Next level!", false)
    }

    private fun checkGameOver(x: Int, y: Int): Unit {
        if (options == 0) {
            if (bonus == 0) {
                showMessage("Game over", "try again!", true)
            } else {
                checkMovement = false
            }
        }
    }

    private fun showMessage(title: String, action: String, gameOver: Boolean): Unit {
        binding.lyMessage.visibility = View.VISIBLE
        binding.tvTitleMessage.text = title
        //  binding.tvAction.text = action

        var score: String = ""
        if (gameOver) {
            score = "Score: " + (levelMoves - moves) + "/" + levelMoves
        } else {
            score = binding.tvTimeData.text.toString()
        }
        binding.tvScoreManager.text = score
        binding.tvNexLevel.text = action
    }

    private fun checkNewBonus(): Unit {
        if (moves % movesRequire == 0) {
            var bonus_x: Int = 0
            var bonus_y: Int = 0
            while (true) {
                bonus_x = (0..7).random()
                bonus_y = (0..7).random()
                if (board[bonus_y][bonus_x] == 0) break
            }
            board[bonus_y][bonus_x] = 2
            paintRequestCell(bonus_x, bonus_y)
        }
    }

    private fun paintRequestCell(bonus_x: Int, bonus_y: Int): Unit {
        var iv =
            findViewById<ImageView>(resources.getIdentifier("c$bonus_x$bonus_y", "id", packageName))
        iv.setImageResource(R.drawable.bonus)
    }

    private fun showCountOnMoves(moves: Int): Unit {
        binding.tbMovesData.text = moves.toString()
    }

    private fun clearOptions(): Unit {
        for (y in 0..7) {
            for (x in 0..7) {
                var contenido: Int = board[y][x]
                if (contenido == 9) {
                    board[y][x] = 0
                    clearOption(x, y)
                }
            }
        }
    }

    private fun clearOption(x: Int, y: Int) {
        var iv = findViewById<ImageView>(resources.getIdentifier("c$x$y", "id", packageName))
        if (checkColorCell(x, y) == "black") iv.setBackgroundColor(
            ContextCompat.getColor(
                this,
                resources.getIdentifier("black_cell", "color", packageName)
            )
        )
        else iv.setBackgroundColor(
            ContextCompat.getColor(
                this,
                resources.getIdentifier("white_cell", "color", packageName)
            )
        )
        if (board[y][x] == 1) iv.setBackgroundColor(
            ContextCompat.getColor(
                this,
                resources.getIdentifier("previus_cell", "color", packageName)
            )
        )
    }

    private fun checkedOption(x: Int, y: Int): Unit {
        //  if (checkMovement) {
        options = 0
        checkedMove(x, y, 1, 2)
        checkedMove(x, y, 2, 1)
        checkedMove(x, y, 1, -2)
        checkedMove(x, y, 2, -1)
        checkedMove(x, y, -1, 2)
        checkedMove(x, y, -2, 1)
        checkedMove(x, y, -1, -2)
        checkedMove(x, y, -2, -1)
        /*   for ((l, a) in board.zip(0..8)) {
               println("$a, ${l.joinToString()}")
           }*/
        showCountOnOption(options)
        //  }
        /*else{
          if(board[y][x]!=1){
              Toast.makeText(this, "nocheck estamos", Toast.LENGTH_SHORT).show()

              binding.tvBonusData.text = if(bonus!=0) bonus.toString() else ""
          }
      }*/


    }

    private fun checkedMove(x: Int, y: Int, mov_x: Int, mov_y: Int) {
        var option_x = x - mov_x
        var option_y = y - mov_y

        if (option_x < 8 && option_y < 8 && option_x >= 0 && option_y >= 0) {
            if (board[option_y][option_x] == 0 || board[option_y][option_x] == 2) {
                options++

                paintOption(option_x, option_y)
                if (board[option_y][option_x] != 2) board[option_y][option_x] = 9

            }
        }
    }
    private fun showCountOnOption(increment: Int){
        binding.tvOptionData.setText(increment.toString())
    }
    private fun paintOption(option_x: Int, option_y: Int): Unit {
        var iv = findViewById<ImageView>(
            resources.getIdentifier(
                "c$option_x$option_y",
                "id",
                packageName
            )
        )
        if (checkColorCell(
                option_x,
                option_y
            ) == "black"
        ) iv.setBackgroundResource(R.drawable.option_black)
        else iv.setBackgroundResource(R.drawable.option_white)
    }

    private fun checkColorCell(x: Int, y: Int): String {
        var par_x = x % 2 == 0
        var par_y = y % 2 == 0
        if (par_x) {
            if (par_y) return "black"
            return "white"
        } else {
            if (par_y) return "white"
            return "black"
        }

    }

    private fun paintHorseCell(x: Int, y: Int, color: String) {
        println("horse cell: $x $y")
        var iv = findViewById<ImageView>(resources.getIdentifier("c$x$y", "id", packageName))
        iv.setBackgroundColor(
            ContextCompat.getColor(
                this,
                resources.getIdentifier(color, "color", packageName)
            )
        )
        iv.setImageResource(R.drawable.caballo)
    }

    fun initScreenGame(): Unit {
        setSizeBoard()
        hideMessage()
    }

    private fun hideMessage(): Unit {
        binding.lyMessage.visibility = android.view.View.INVISIBLE
    }

    private fun setSizeBoard(): Unit {
        var iv: ImageView
        val display = windowManager.defaultDisplay

        val size = Point()
        display.getSize(size)

        val width = size.x
        var width_dp =  (width/ getResources().getDisplayMetrics().density)

        var lateralMargin= 0
        var width_cell = (width_dp-lateralMargin)/8
        var heigth_cell = width_cell

        bonusWidth = 2 * width_cell.toInt()
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