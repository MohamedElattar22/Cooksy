package com.example.cooksy.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cooksy.home.HomeActivity
import com.example.cooksy.databinding.RegisterLayoutBinding
import com.example.cooksy.dataclasses.showMessage
import com.example.cooksy.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : RegisterLayoutBinding
    lateinit var viewModel : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initialize registerViewModel
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = this
        subscribeToLiveData()
        //main  activity - main thread
        binding.navigateToLogin.setOnClickListener {
            navigateToLogin()
        }

    }


    private fun subscribeToLiveData() {
        viewModel.errorLiveData.observe(this){ error->
            showMessage(
                title = "Try again",
                message = error.message?:"something went wrong",
                posAction = { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                , posMessage = "ok"

            )
        }
        viewModel.openActivity.observe(this){it ->
            if(it==true){
              navigateToHome()
            }

        }
    }
    fun navigateToHome(){
        val inten = Intent(this , HomeActivity::class.java )
        startActivity(intent)
    }
    fun navigateToLogin(){
        val intent = Intent(this , LoginActivity::class.java)
        startActivity(intent)
    }

}