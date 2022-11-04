package com.findout.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.findout.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

const val START_SCREEN = "startScreen"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()


    companion object {
        fun newIntent(context: Context, startScreen: String? = ""): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(START_SCREEN, startScreen)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}