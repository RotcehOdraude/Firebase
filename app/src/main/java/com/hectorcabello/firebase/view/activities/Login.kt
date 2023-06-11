package com.hectorcabello.firebase.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hectorcabello.firebase.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}