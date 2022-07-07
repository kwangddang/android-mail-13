package com.example.woowahan_mail.view.main

import androidx.lifecycle.ViewModel
import com.example.woowahan_mail.data.DummyData
import com.example.woowahan_mail.view.main.mail.MailFragment

class MainViewModel: ViewModel() {
    var currentFocus = MainActivity.SELECTED_MAIL
    var currentMail = MailFragment.PRIMARY
}