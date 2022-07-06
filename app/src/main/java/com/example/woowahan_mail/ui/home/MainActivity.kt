package com.example.woowahan_mail.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Test",intent.getStringExtra(this.getString(R.string.name))!!)
        Log.d("Test",intent.getStringExtra(this.getString(R.string.email))!!)
    }
}