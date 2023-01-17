package com.example.caballo

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.caballo.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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
    private var nameBlackColor: String = "black_cell"
    private var nameWhiteColor: String = "white_cell"
    private var gaming: Boolean = true

    //contador
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //resetBoard()
        // setFirstPosition()
        initAds()
        initScreenGame()
        startGame()
        getReadyAds()

        adsNew()


    }
    fun showAdsNew():Unit{
        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
  override fun onAdClicked() {
    // Called when a click is recorded for an ad.
    Log.d(TAG, "Ad was clicked.")
  }

  override fun onAdDismissedFullScreenContent() {
    // Called when ad is dismissed.
    Log.d(TAG, "Ad dismissed fullscreen content.")
    mInterstitialAd = null
  }

  override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
    // Called when ad fails to show.
    Log.e(TAG, "Ad failed to show fullscreen content.")
    mInterstitialAd = null
  }

  override fun onAdImpression() {
    // Called when an impression is recorded for an ad.
    Log.d(TAG, "Ad recorded an impression.")
  }

  override fun onAdShowedFullScreenContent() {
    // Called when ad is shown.
    Log.d(TAG, "Ad showed fullscreen content.")
  }
}
    }

    fun adsNew():Unit{
         var adRequest = AdRequest.Builder().build()

      InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
        override fun onAdFailedToLoad(adError: LoadAdError) {
          Log.d(TAG, adError?.toString())
          mInterstitialAd = null
        }

        override fun onAdLoaded(interstitialAd: InterstitialAd) {
          Log.d(TAG, 'Ad was loaded.')
          mInterstitialAd = interstitialAd
        }
      })
    }

    var mInterstitialAd: InterstitialAd? = null

    private fun showInterstital():Unit{
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                override fun onAdClicked() {
                    // Called when a click is recorded for an ad.
                  //  Log.d(TAG, "Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                 //   Log.d(TAG, "Ad dismissed fullscreen content.")
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    // Called when ad fails to show.
                 //   Log.e(TAG, "Ad failed to show fullscreen content.")
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    // Called when an impression is recorded for an ad.
                 //   Log.d(TAG, "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                  //  Log.d(TAG, "Ad showed fullscreen content.")
                }
            }
            mInterstitialAd?.show(this)
        }
    }

    private fun getReadyAds():Unit{
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
             //   adError?.toString()?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
               // Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })
    }

    private fun initAds(): Unit {
        MobileAds.initialize(this) {}

        val adView = AdView(this)
        adView.setAdSize(AdSize.BANNER)
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        binding.lyAdsBanner.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
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

    private fun resetTime(): Unit {
        mHandler?.removeCallbacks(chronometer)
        timeInSeconds = 0

        binding.tvTimeData.text = "00:00"
    }

    private fun startTime(): Unit {
        mHandler = Handler(Looper.getMainLooper())
        chronometer.run()
    }

    private var timeInSeconds: Long = 0
    private fun updateStopWatchView(timeInSeconds: Long): Unit {
        val formattedTime = getFormattedStopWatch(timeInSeconds * 1000)
        binding.tvTimeData.text = formattedTime
    }

    private fun getFormattedStopWatch(ms: Long): String {
        var miliSeconds = ms
        val minutes = TimeUnit.MILLISECONDS.toMinutes(ms)
        miliSeconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(miliSeconds)
        return "${if (minutes < 10) "0" else ""}$minutes:${if (seconds < 10) "0" else ""}$seconds"
    }

    private var chronometer: Runnable = object : Runnable {
        override fun run() {
            try {
                if(gaming){
                    timeInSeconds++
                    updateStopWatchView(timeInSeconds)
                }
            } finally {
                mHandler!!.postDelayed(this, 1000L)
            }
        }
    }

    private fun clearBoard() {
        var iv: ImageView
        var colorBlack = ContextCompat.getColor(
            this,
            resources.getIdentifier(nameBlackColor, "color", packageName)
        )
        var whiteColor = ContextCompat.getColor(
            this,
            resources.getIdentifier(nameWhiteColor, "color", packageName)
        )
        for (x in 0..7) {
            for (y in 0..7) {
                iv = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
                iv.setImageResource(0)
                if (checkColorCell(x, y) == "black") iv.setBackgroundColor(colorBlack)
                else iv.setBackgroundColor(whiteColor)
            }
        }
    }

    private fun startGame() {
        gaming = true
        resetBoard()
        clearBoard()
        setFirstPosition()

        resetTime()
        startTime()
    }

    private fun painAllOptions() {
        for (y in 0..7) {
            for (x in 0..7) {
                if (board[y][y] != 1 || board[y][x] == 2) {
                    board[y][y] = 9
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
        gaming = false
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
    private var bitmap: Bitmap?=null
    fun shareGame():Unit {
        // ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
            1
        )
        //permisos.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        //shouldShowRequestPermissionRationale(android.Manifest.permission.ACCEPT_HANDOVER)


    }


    val permisos = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            permiso: Boolean->
        if(permiso){
            Snackbar.make(binding.root, "Debes otorgar permisos para continuar", Snackbar.LENGTH_SHORT).show()
            binding.root.isDrawingCacheEnabled = true
            binding.root.buildDrawingCache()
            bitmap = Bitmap.createBitmap(binding.root.drawingCache)
            binding.root.isDrawingCacheEnabled = false

            if(bitmap!= null){
                var idGame= SimpleDateFormat("yyyy/MM/dd").format(Date())
                idGame=idGame.replace(":", "")
                idGame=idGame.replace("/", "")

                val path= savedImage(bitmap, "${idGame}.jpg")

                val bmpUri = Uri.parse(path)

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra(Intent.EXTRA_STREAM, bmpUri)
                    putExtra(Intent.EXTRA_TEXT, "mensaje para enviar")
                    type = "image/png"
                }
                val finalShareIntent = Intent.createChooser(shareIntent, "selecciona una app")
                finalShareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                this.startActivity(finalShareIntent)

            }
        }else{
            Snackbar.make(binding.root, "Debes otorgar permisos para continuar", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun savedImage(bitmap: Bitmap?, fileName: String): String? {
        if (bitmap == null) {
            return null
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + "/Screenshots"
                )
            }

             val uri= this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

             if(uri!=null){
                this.contentResolver.openOutputStream(uri).use {
                    if(it==null){
                        return@use
                    }
                    bitmap.compress(Bitmap.CompressFormat.PNG, 85, it)
                    it.flush()
                    it.close()
                    MediaScannerConnection.scanFile(this, arrayOf(uri.toString()), null, null)

                }

             }
             return uri.toString()
         }
        val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/Screenshots").absolutePath

        val dir= File(filePath)
        if(!dir.exists()) dir.mkdirs()
        val file = File(dir, fileName)
        val fOut = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut)
        fOut.flush()
        fOut.close()

        MediaScannerConnection.scanFile(this, arrayOf(file.toString()), null, null)
        return  filePath
    }
    fun compartir():Unit{

    }
    fun launchedShareGame(v: View){
        shareGame()
    }
}