package com.yourcompany.bullseye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yourcompany.bullseye.databinding.ActivityAboutBinding
import com.yourcompany.bullseye.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.about_page_title)

        binding.backButton?.setOnClickListener {
            finish()
        }


    }



}
