package com.example.printfulchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.printfulchallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) //Set AppTheme back once MainActivity is created
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}