package com.hectorcabello.firebase.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
//import android.view.View
import com.hectorcabello.firebase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickStaff(view: View) {
        val intent = Intent(this, Characters::class.java)
        val bundle = Bundle()
        bundle.putString("seleccion", "Staff")
        intent.putExtras(bundle)
        startActivity(intent)
    }
    fun onClickStudent(view: View) {
        val intent = Intent(this, Characters::class.java)
        val bundle = Bundle()
        bundle.putString("seleccion", "Student")
        intent.putExtras(bundle)
        startActivity(intent)
    }

}