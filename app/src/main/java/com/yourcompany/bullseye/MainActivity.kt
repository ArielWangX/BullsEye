package com.yourcompany.bullseye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import com.yourcompany.bullseye.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var sliderValue = 0
    private var targetValue = newTargetValue()
    private var totalScore = 0
    private var totalRound = 1

    private lateinit var binding: ActivityMainBinding        //binding type based on layout file (need dependency)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater)   //convert layout file to corresponding view object which can be used in Kotlin
        val view = binding.root    //access the root view of the layout file
        setContentView(view)    //set the content view of this activity to the view variable
        //finish assess to layout file.

        startNewGame()

        binding.hitMeButton.setOnClickListener {
//            Log.i("Button Click Event", "You clicked the Hit Me Button")
            showResult()
            totalScore += pointsForCurrentRound()
            binding.gameScoreTextView?.text = totalScore.toString() // textview only accept strings, not integers.
        }

        binding.startOverButton?.setOnClickListener {
            startNewGame()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sliderValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

    }

    private fun differenceAmount() = abs(targetValue - sliderValue)    //single expression function

    private fun newTargetValue() = Random.nextInt(1, 100)

    private fun pointsForCurrentRound(): Int {
        val maxScore = 100
        val difference = differenceAmount()
        val bonusPoints = if (difference == 0) {
            100
        } else if (difference == 1) {
            50
        } else (
                0
        )
        return maxScore + bonusPoints - difference

//        var difference = if (targetValue > sliderValue) {
//            targetValue - sliderValue
//        } else if (targetValue < sliderValue) {
//            sliderValue - targetValue
//        } else /*(targetValue == sliderValue)*/ {
//            0
//        }

//        var difference: Int = targetValue - sliderValue
//
//        if (difference < 0) {
//            difference *= (-1)
//        }
    }

    private fun startNewGame() {
        totalScore = 0
        totalRound = 1
        sliderValue = 50
        targetValue = newTargetValue()

        binding.gameScoreTextView?.text = totalScore.toString()
        binding.gameRoundTextView?.text = totalRound.toString()
        binding.targetTextView.text = targetValue.toString()
        binding.seekBar.progress = sliderValue
    }

    private fun showResult() {
        val dialogTitle = alertTitle()
        val dialogMessage =
            getString(R.string.result_dialog_message, sliderValue, pointsForCurrentRound())

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.setPositiveButton(R.string.result_dialog_button_text) { dialog, _ ->
            dialog.dismiss()

            targetValue = newTargetValue()        //generate new targetValue
            binding.targetTextView.text = targetValue.toString()

            totalRound += 1     //round increment
            binding.gameRoundTextView?.text = totalRound.toString()

        }

        builder.create().show()
    }

    private fun alertTitle(): String {
        val difference = differenceAmount()
        val title: String = when {
            difference == 0 -> {
                getString(R.string.alert_title_1)
            }
            difference == 1 -> {
                getString(R.string.alert_title_5)
            }
            difference < 5 -> {
                getString(R.string.alert_title_2)
            }
            difference <= 10 -> {
                getString(R.string.alert_title_3)
            }
            else -> {
                getString(R.string.alert_title_4)
            }
        }

        return title

        //        var title: String = if (difference == 0) {
//            getString(R.string.alert_title_1)
//        } else if (difference < 5 ) {
//            getString(R.string.alert_title_2)
//        } else if (difference <= 10) {
//            getString(R.string.alert_title_3)
//        } else {
//            getString(R.string.alert_title_4)
//        }
    }

}