package com.ian.clearscoreinterview.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BrandScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}