package com.yourcompany.bullseye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import com.yourcompany.bullseye.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var sliderValue = 0
    private var targetValue = Random.nextInt(1, 100)

    private lateinit var binding: ActivityMainBinding        //binding type based on layout file (need dependency)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater)   //convert layout file to corresponding view object which can be used in Kotlin
        val view = binding.root    //access the root view of the layout file
        setContentView(view)    //set the content view of this activity to the view variable
        //finish assess to layout file.

        binding.targetTextView.text = targetValue.toString()

        binding.hitMeButton.setOnClickListener {
            Log.i("Button Click Event", "You clicked the Hit Me Button")
            showResult()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sliderValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

    }

    private fun pointsForCurrentRound(): Int {
        val maxScore = 100

//        var difference = if (targetValue > sliderValue) {
//            targetValue - sliderValue
//        } else if (targetValue < sliderValue) {
//            sliderValue - targetValue
//        } else /*(targetValue = sliderValue)*/ {
//            0
//        }

//        var difference: Int = targetValue - sliderValue
//
//        if (difference < 0) {
//            difference *= (-1)
//        }

        val difference = abs(targetValue - sliderValue)

        return maxScore - difference
    }

    private fun showResult() {
        val dialogTitle = getString(R.string.result_dialog_title)
        val dialogMessage =
            getString(R.string.result_dialog_message, sliderValue, pointsForCurrentRound())
//        val dialogMessage = "The slider's value is $sliderValue"

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.setPositiveButton(R.string.result_dialog_button_text) { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }

}