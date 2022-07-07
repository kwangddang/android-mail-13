package com.example.woowahan_mail.view.login

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object LoginBindingAdapter {
    @JvmStatic
    @BindingAdapter("nameValidation")
    fun setNameValidation(view: EditText, text: String) {

    }

    @JvmStatic
    @BindingAdapter("nameValidationAttrChanged")
    fun setNameValidationInverseBindingListener(view: EditText, listener: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                listener.onChange()
            }

        })
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "nameValidation", event = "nameValidationAttrChanged")
    fun getNameValidation(view: EditText): String = view.text.toString()

    @JvmStatic
    @BindingAdapter("emailValidation")
    fun setEmailValidation(view: EditText, text: String) {

    }

    @JvmStatic
    @BindingAdapter("emailValidationAttrChanged")
    fun setEmailValidationInverseBindingListener(view: EditText, listener: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                listener.onChange()
            }

        })
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "emailValidation", event = "emailValidationAttrChanged")
    fun getEmailValidation(view: EditText): String = view.text.toString()
}