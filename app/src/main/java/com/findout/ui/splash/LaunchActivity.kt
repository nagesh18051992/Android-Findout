package com.findout.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.findout.databinding.ActivityLaunchBinding
import com.findout.ui.main.MainActivity
import com.findout.utils.Constants.LOGIN_SCREEN
import com.findout.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private val viewModel: LaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        val binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.init()
        observeUI()
        splashScreen.setKeepOnScreenCondition { true }
        startSomeNextActivity()
        finish()

    }

    private fun startSomeNextActivity(){
        startActivity(MainActivity.newIntent(this,LOGIN_SCREEN))
        finish()
    }

    private fun observeUI(){
        viewModel.appUpdate.observe(this) {
            when(it) {
                is NetworkResult.Success -> {

                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }
}