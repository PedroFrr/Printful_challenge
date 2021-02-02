package com.example.printfulchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.printfulchallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}