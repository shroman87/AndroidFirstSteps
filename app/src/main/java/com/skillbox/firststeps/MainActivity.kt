package com.skillbox.firststeps

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    val button: Button
        get() = findViewById(R.id.button)

    val buttonstart: Button
        get() = findViewById(R.id.buttonstart)

    val textView: TextView
        get() = findViewById(R.id.textView)

    val textViewClick: TextView
        get() = findViewById(R.id.textViewClick)

    val progressBar: ProgressBar
        get() = findViewById(R.id.progressBar)

    var counter = 0
    var counterclick = 0
    var counterrecord = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if (counter < 10) {
                counterclick += 1
                textViewClick.text = "Количество нажатий: $counterclick"
            }
        }

        buttonstart.setOnClickListener {
            counterclick = 0
            counter = 0
            progressBar.progress = 0

            textViewClick.text = "Количество нажатий: $counterclick"
            timer.cancel()
            timer.start()

            ObjectAnimator
                .ofInt(progressBar, "progress", 100)
                .setDuration(10000)
                .start()
        }


    }

    var timer = object : CountDownTimer(10_000L, 1_000L) {
        override fun onTick(p0: Long) {
            counter += 1
            textView.text = "Время пошло $counter"
        }

        override fun onFinish() {
            if (counterclick > counterrecord) {
                counterrecord = counterclick
            }

            textView.text = "Время вышло. Рекорд = $counterrecord нажатий"
        }


    }

}