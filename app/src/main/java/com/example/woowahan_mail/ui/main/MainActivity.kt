package com.example.woowahan_mail.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.ActivityMainBinding
import com.example.woowahan_mail.getDeviceWidth
import com.example.woowahan_mail.ui.main.mail.MailFragment
import com.example.woowahan_mail.ui.main.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mailFragment = MailFragment()
    private val settingFragment = SettingFragment()

    private val viewModel: MainViewModel by viewModels()

    private val menuItemClickListener: (MenuItem) -> Boolean = { menuItem ->
        if (menuItem.itemId == R.id.item_main_menu_mail) {
            showMailFragment()
            viewModel.currentFocus = SELECTED_MAIL
            true
        } else if (menuItem.itemId == R.id.item_main_menu_setting) {
            showSettingFragment()
            viewModel.currentFocus = SELECTED_SETTING
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

    private fun checkDeviceWidth() {
        if (getDeviceWidth() > 600) {
            setNavigationRail()
        } else {
            setBottomNavigation()
        }
    }

    private fun showMailFragment() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, mailFragment).commit()
    }

    private fun showSettingFragment() {
        putUserData()
        val fm = supportFragmentManager
        if (fm.backStackEntryCount == 0) {
            fm.beginTransaction().replace(R.id.fragment_container_main, settingFragment, this.getString(R.string.setting_fragment))
                .addToBackStack(this.getString(R.string.setting_fragment)).commit()
        }
        else {
            if (!(fm.getBackStackEntryAt(fm.backStackEntryCount - 1).name.equals(this.getString(R.string.setting_fragment)))) {
                fm.beginTransaction().replace(R.id.fragment_container_main, settingFragment, this.getString(R.string.setting_fragment))
                    .addToBackStack(this.getString(R.string.setting_fragment)).commit()
            }
        }
    }

    private fun putUserData() {
        val bundle = Bundle()
        val nameTag = this.getString(R.string.name)
        val emailTag = this.getString(R.string.email)
        val name = intent.getStringExtra(nameTag)
        val email = intent.getStringExtra(emailTag)
        bundle.putString(nameTag, name)
        bundle.putString(emailTag, email)
        settingFragment.arguments = bundle
    }

    private fun setNavigationRail() {
        initNavigationRailMenu()
        setNavigationRailSelectedIcon()
        setNavigationRailClickListener()
    }

    private fun setBottomNavigation() {
        initBottomNavigationMenu()
        setBottomNavigationSelectedIcon()
        setBottomNavigationClickListener()
    }

    private fun initNavigationRailMenu() {
        binding.bottomNavigationMain.visibility = View.GONE
        binding.navigationRailMain.apply {
            visibility = View.VISIBLE
            menu.clear()
            inflateMenu(R.menu.main_menu)
        }
    }

    private fun setNavigationRailSelectedIcon() {
        if (viewModel.currentFocus == SELECTED_MAIL) {
            binding.navigationRailMain.menu.findItem(R.id.item_main_menu_mail).isChecked = true
        } else {
            binding.navigationRailMain.menu.findItem(R.id.item_main_menu_setting).isChecked = true
        }
    }

    private fun setNavigationRailClickListener() {
        binding.navigationRailMain.setOnItemSelectedListener(menuItemClickListener)
    }

    private fun initBottomNavigationMenu() {
        binding.navigationRailMain.visibility = View.GONE
        binding.bottomNavigationMain.apply {
            visibility = View.VISIBLE
            menu.clear()
            inflateMenu(R.menu.main_menu)
        }
    }

    private fun setBottomNavigationSelectedIcon() {
        if (viewModel.currentFocus == SELECTED_MAIL) {
            binding.bottomNavigationMain.menu.findItem(R.id.item_main_menu_mail).isChecked = true
        } else {
            binding.bottomNavigationMain.menu.findItem(R.id.item_main_menu_setting).isChecked = true
        }
    }

    private fun setBottomNavigationClickListener() {
        binding.bottomNavigationMain.setOnItemSelectedListener(menuItemClickListener)
    }

    companion object {
        const val SELECTED_MAIL = 1
        const val SELECTED_SETTING = 2
    }
}