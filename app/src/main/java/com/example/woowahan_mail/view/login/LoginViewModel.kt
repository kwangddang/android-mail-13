package com.example.woowahan_mail.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    var nameSuccess = false
    var emailSuccess = false
}