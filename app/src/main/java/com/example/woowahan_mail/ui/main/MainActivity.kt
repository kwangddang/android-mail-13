package com.example.woowahan_mail.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.ActivityMainBinding
import com.example.woowahan_mail.ui.main.mail.MailFragment
import com.example.woowahan_mail.ui.main.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mailFragment = MailFragment()
    private val settingFragment = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setScreenToMailFragment()
        setBottomNavigationClickListener()
    }

    private fun setScreenToMailFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, mailFragment).commit()
    }

    private fun setScreenToSettingFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, settingFragment).commit()
    }

    private fun setBottomNavigationClickListener() {
        binding.bottomNavigationMain.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.item_main_menu_mail) {
                setScreenToMailFragment()
                true
            } else if (menuItem.itemId == R.id.item_main_menu_setting) {
                setScreenToSettingFragment()
                true
            } else {
                false
            }
        }
    }
}