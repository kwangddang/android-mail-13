package com.example.woowahan_mail.view.main.mail

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.woowahan_mail.R

object MailBindingAdapter {
    @JvmStatic
    @BindingAdapter("addresser")
    fun setAddresser(view: TextView, addresser: String) {
        if (addresser[0] in 'A'..'z')
            view.text = addresser[0].uppercase()
        else {
            view.setBackgroundResource(R.drawable.ic_person)
        }
    }

    @JvmStatic
    @BindingAdapter("profile")
    fun setProfile(view: ImageView, addresser: String) {
        if (addresser[0] in 'A'..'z') {
            val colorList = arrayListOf<Int>()
            colorList.add(ContextCompat.getColor(view.context, R.color.red))
            colorList.add(ContextCompat.getColor(view.context, R.color.orange))
            colorList.add(ContextCompat.getColor(view.context, R.color.green))
            colorList.add(ContextCompat.getColor(view.context, R.color.blue))
            colorList.add(ContextCompat.getColor(view.context, R.color.indigo))
            colorList.add(ContextCompat.getColor(view.context, R.color.purple))
            val index = (0..5).random()
            view.backgroundTintList = ColorStateList.valueOf(colorList[index])
        } else {
            view.setBackgroundResource(R.drawable.cirecle_background_gray)
        }
    }
}