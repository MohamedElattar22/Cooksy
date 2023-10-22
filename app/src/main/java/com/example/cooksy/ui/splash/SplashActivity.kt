package com.example.cooksy.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooksy.login.LoginActivity
import com.example.cooksy.databinding.SplashLayoutBinding


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
   private lateinit var binding : SplashLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =SplashLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startbtn.setOnClickListener {

            startActivityLogin()
        }

    }

    private fun startActivityLogin() {
        val intent = Intent(this , LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}