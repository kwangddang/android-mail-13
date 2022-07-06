package com.example.woowahan_mail.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var email = MutableLiveData("")
    var name = MutableLiveData("")
}