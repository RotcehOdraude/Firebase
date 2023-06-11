package com.hectorcabello.firebase.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hectorcabello.firebase.databinding.ActivitySplashScreenBinding
import kotlin.concurrent.thread

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        thread {
            Thread.sleep(4000)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}