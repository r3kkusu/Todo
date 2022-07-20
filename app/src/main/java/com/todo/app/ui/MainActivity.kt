package com.todo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.todo.app.R

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}