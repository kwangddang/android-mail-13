package com.example.woowahan_mail.view.main.mail

import android.widget.TextView
import androidx.databinding.BindingAdapter

object MailBindingAdapter {
    @JvmStatic
    @BindingAdapter("addresser")
    fun setAddresser(view: TextView, addresser: String){
        view.text = addresser[0].uppercase()
    }
}