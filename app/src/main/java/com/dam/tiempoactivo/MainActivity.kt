package com.dam.tiempoactivo

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dam.tiempoactivo.ui.theme.TiempoActivoTheme

class MainActivity : ComponentActivity() {
    private var startTime = 0L
    private val handler = Handler()
    private var millisecondTime: Long = 0
    private var updateTime = 0L
    private var tiempo = " "
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            TiempoActivoTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        resetTimer()
        startTimer()
    }


    override fun onRestart() {
        super.onRestart()
        Log.d("Estado", "estamose en restart")


        setContent {

                tiempoo(tiempo)


        }
        }


        fun startTimer() {
            startTime = SystemClock.uptimeMillis()
            handler.postDelayed(runnable, 0)
        }

        fun pauseTimer() {
            updateTime += millisecondTime
            handler.removeCallbacks(runnable)
        }

        fun resetTimer() {
            millisecondTime = 0L
            startTime = 0L
            updateTime = 0L
            tiempo = "00:00:00"
        }

        private val runnable: Runnable = object : Runnable {

            override fun run() {
                millisecondTime = SystemClock.uptimeMillis() - startTime
                var seconds = (millisecondTime / 1000).toInt()
                var minutes = seconds / 60
                val hours = minutes / 60
                seconds %= 60
                minutes %= 60
                tiempo = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                handler.postDelayed(this, 0)
            }


        }

        override fun onPause() {
            super.onPause()

            Log.d("Tiempo ejecutado", tiempo.toString())
        }
    }

    @Composable
    fun tiempoo(tiempo: String) {
        Text(
           text= (tiempo.toString())
        )
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TiempoActivoTheme {
            Greeting("Android")
        }
    }

