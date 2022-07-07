package com.example.woowahan_mail.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.ActivityMainBinding
import com.example.woowahan_mail.view.getDeviceWidth
import com.example.woowahan_mail.view.main.mail.MailFragment
import com.example.woowahan_mail.view.main.mail.primary.PrimaryFragment
import com.example.woowahan_mail.view.main.mail.promotions.PromotionsFragment
import com.example.woowahan_mail.view.main.setting.SettingFragment
import com.example.woowahan_mail.view.main.mail.social.SocialFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mailFragment = MailFragment()
    private val settingFragment = SettingFragment()
    private val primaryFragment = PrimaryFragment()
    private val socialFragment = SocialFragment()
    private val promotionsFragment = PromotionsFragment()

    private val viewModel: MainViewModel by viewModels()

    private val fm: FragmentManager by lazy {
        supportFragmentManager
    }

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

    private val drawerMenuItemClickListener: (MenuItem) -> Boolean = { menuItem ->
        when (menuItem.itemId) {
            R.id.item_drawer_menu_primary -> {
                showPrimaryFragment()
                binding.drawerMainContainer.closeDrawer(GravityCompat.START)
                true
            }
            R.id.item_drawer_menu_social -> {
                showSocialFragment()
                binding.drawerMainContainer.closeDrawer(GravityCompat.START)
                true
            }
            R.id.item_drawer_menu_promotions -> {
                showPromotionsFragment()
                binding.drawerMainContainer.closeDrawer(GravityCompat.START)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkDeviceWidth()
        setOnClickListeners()
    }

    private fun checkDeviceWidth() {
        if (getDeviceWidth() > 600) {
            setNavigationRail()
        } else {
            setBottomNavigation()
        }
    }

    private fun setOnClickListeners(){
        binding.imgToolbarMainMore.setOnClickListener{ binding.drawerMainContainer.openDrawer(GravityCompat.START) }
        binding.navigationMainDrawer.setNavigationItemSelectedListener(drawerMenuItemClickListener)
    }

    private fun showMailFragment() {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fm.beginTransaction().replace(R.id.fragment_container_main, mailFragment).commit()
    }

    private fun showSettingFragment() {
        putUserData()
        setFragmentBackStack(R.id.fragment_container_main, this.getString(R.string.setting_fragment), settingFragment)
    }

    private fun showPrimaryFragment() {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fm.beginTransaction().replace(R.id.fragment_container_mail, primaryFragment).commit()
    }

    private fun showSocialFragment() {
        setFragmentBackStack(R.id.fragment_container_mail, this.getString(R.string.social_fragment), socialFragment)
    }

    private fun showPromotionsFragment() {
        setFragmentBackStack(R.id.fragment_container_mail, this.getString(R.string.promotions_fragment), promotionsFragment)
    }

    private fun setFragmentBackStack(container: Int, tag: String, fragment: Fragment){
        if (fm.backStackEntryCount == 0) {
            fm.beginTransaction().replace(container, fragment, tag).addToBackStack(tag).commit()
        }
        else if(fm.getBackStackEntryAt(fm.backStackEntryCount - 1).name == tag)
        else {
            fm.popBackStack()
            fm.beginTransaction().replace(container, fragment, tag).addToBackStack(tag).commit()
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

        const val SELECTED_PRIMARY = 0
        const val SELECTED_SOCIAL = 1
        const val SELECTED_PROMOTIONS = 2
    }
}