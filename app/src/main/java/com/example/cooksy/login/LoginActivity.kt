package com.example.cooksy.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cooksy.home.HomeActivity
import com.example.cooksy.databinding.ActivityLoginBinding
import com.example.cooksy.dataclasses.showMessage
import com.example.cooksy.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel : LoginViewModel
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner=this
        subscribeToLiveData()
        binding.navigateToSignUp.setOnClickListener {
            navigateToSignUp()
        }

    }
    fun subscribeToLiveData(){
        viewModel.errorEstate.observe(this){
            error->
            showMessage(
                title = "Try Again",
                message = "Email or Password are incorrect",
                posAction = { dialogInterface, i ->
                    dialogInterface.dismiss()
                } ,
                posMessage = "Ok"
            )
        }
        viewModel.navigateHomeActivity.observe(this){
            nav->
            if(nav==true){
                navigateToHome()
            }
        }

    }
    fun navigateToHome(){
        val intent = Intent(this , HomeActivity::class.java )
        startActivity(intent)
    }
    fun navigateToSignUp(){
        val intent = Intent(this , RegisterActivity::class.java )
        startActivity(intent)
    }
}