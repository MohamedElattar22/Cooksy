package com.example.cooksy.register
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cooksy.dataclasses.ErrorContainer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    // data -> view
    // create error dialog - succ
    // view
    //live data
    val errorLiveData = MutableLiveData<ErrorContainer>()
    val openActivity = MutableLiveData<Boolean>()
    val isloading = MutableLiveData<Boolean>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val userNameError = MutableLiveData<String?>()
    val emailError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()
    val auth  = Firebase.auth


    fun registerNewAccount(){

        if(!isRegisterValid()){
            return
        }
        isloading.value = true
        auth.createUserWithEmailAndPassword(email.value!! , password.value!!)
            .addOnCompleteListener {
                task->
                isloading.value=false
                if(task.isSuccessful){
                    openActivity.postValue(true)

                }
                else{
                    openActivity.postValue(false)
                    errorLiveData.postValue(
                        ErrorContainer(
                           message = task.exception?.localizedMessage
                        )
                    )

                }
            }

    }
   private fun isRegisterValid() : Boolean{
        var valid = true
        if(userName.value.isNullOrBlank()){
            //show error
            userNameError.postValue("Enter valid user name")
            valid = false
        }
       else{
           userNameError.postValue(null)
        }
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