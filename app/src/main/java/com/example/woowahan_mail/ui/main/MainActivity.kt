package com.example.woowahan_mail.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.ActivityMainBinding
import com.example.woowahan_mail.getDeviceWidth
import com.example.woowahan_mail.ui.main.mail.MailFragment
import com.example.woowahan_mail.ui.main.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mailFragment = MailFragment()
    private val settingFragment = SettingFragment()

    private val menuItemClickListener: (MenuItem) -> Boolean = { menuItem ->
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkDeviceWidth()
    }

    private fun checkDeviceWidth(){
        if(getDeviceWidth() > 600){
            setNavigationRail()
        }
        else{
            setBottomNavigation()
        }
    }

    private fun setScreenToMailFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, mailFragment).commit()
    }

    private fun setScreenToSettingFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, settingFragment).commit()
    }

    private fun setNavigationRail(){
        binding.navigationRailMain.visibility = View.VISIBLE
        binding.bottomNavigationMain.visibility = View.GONE
        setNavigationRailClickListener()
    }

    private fun setBottomNavigation(){
        binding.bottomNavigationMain.visibility = View.VISIBLE
        binding.navigationRailMain.visibility = View.GONE
        setBottomNavigationClickListener()
    }

    private fun setBottomNavigationClickListener() {
        binding.bottomNavigationMain.setOnItemSelectedListener(menuItemClickListener)
    }

    private fun setNavigationRailClickListener() {
        binding.navigationRailMain.setOnItemSelectedListener(menuItemClickListener)
    }
}