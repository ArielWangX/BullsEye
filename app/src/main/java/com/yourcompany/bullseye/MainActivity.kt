package com.yourcompany.bullseye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yourcompany.bullseye.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding        //binding type based on layout file (need dependency)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   //convert layout file to corresponding view object which can be used in Kotlin
        val view = binding.root    //access the root view of the layout file
        setContentView(view)    //set the content view of this activity to the view variable
                                    //finish assess to layout file.

        binding.targetTextView.text = "52"  //start to assess the view and change the text.
        binding.hitMeButton.height = 200
    }
}