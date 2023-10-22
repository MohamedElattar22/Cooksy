package com.example.cooksy.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cooksy.dataclasses.ErrorContainer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var navigateHomeActivity = MutableLiveData<Boolean>()
    var errorEstate = MutableLiveData<ErrorContainer>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var emailError = MutableLiveData<String?>()
    var passwordError = MutableLiveData<String?>()
    val auth = Firebase.auth

    fun loginUser(){
        if(!isLoginValid()){
            return
        }
        isLoading.value = true
        auth.signInWithEmailAndPassword(
            email.value!! , password.value!!
        )
            .addOnCompleteListener {
                task->
                isLoading.value=false
                if(task.isSuccessful){
                    navigateHomeActivity.postValue(true)
                }
                else{
                    navigateHomeActivity.postValue(false)
                    errorEstate.postValue(
                        ErrorContainer(
                            message = task.exception?.localizedMessage
                        )
                    )

                }
            }

    }



    private fun isLoginValid() : Boolean{
        var valid = true

        if(email.value.isNullOrBlank()){
            //show error
            emailError.postValue("Enter valid email")
            valid = false

        }
        else{
            emailError.postValue(null)
        }
        if(password.value.isNullOrBlank()){
            //show error
            passwordError.postValue("Enter valid password")
            valid = false

        }
        else{
            passwordError.postValue(null)
        }

        return valid

    }
}