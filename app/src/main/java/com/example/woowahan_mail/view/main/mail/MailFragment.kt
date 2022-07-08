package com.example.woowahan_mail.view.main.mail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.woowahan_mail.R
import com.example.woowahan_mail.data.DummyData
import com.example.woowahan_mail.data.Mail
import com.example.woowahan_mail.databinding.FragmentMailBinding
import com.example.woowahan_mail.view.getDeviceWidth
import com.example.woowahan_mail.view.getMainActivity
import com.example.woowahan_mail.view.main.MainViewModel
import com.example.woowahan_mail.view.setDrawerIconColor
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigationrail.NavigationRailView

class MailFragment: Fragment() {
    private var _binding: FragmentMailBinding? = null
    private val binding get() = _binding!!

    private val adapter: MailAdapter by lazy { MailAdapter() }

    private val viewModel: MainViewModel by activityViewModels()

    private var dummyData: List<Mail>? = null

    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setIcon()
        checkMailType()
        setAdapter()
    }

    private fun setIcon() {
        if(requireActivity().getDeviceWidth() > 600){
            getMainActivity().findViewById<NavigationRailView>(R.id.navigation_rail_main).menu.findItem(
                R.id.item_main_menu_mail
            ).isChecked = true
        }
        else{
            getMainActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_main).menu.findItem(
                R.id.item_main_menu_mail
            ).isChecked = true
        }
    }

    private fun setAdapter() {
        adapter.setDummyData(dummyData!!)
        binding.recyclerPrimary.adapter = adapter
    }

    private fun checkMailType(){
        if(viewModel.currentMail == PRIMARY) {
            dummyData = DummyData.primaryMail
            binding.textPrimaryTitle.text = requireContext().getString(R.string.primary_title)
        }
        else if(viewModel.currentMail == SOCIAL) {
            dummyData = DummyData.socialMail
            binding.textPrimaryTitle.text = requireContext().getString(R.string.social_title)
        }
        else if(viewModel.currentMail == PROMOTIONS) {
            dummyData = DummyData.promotionsMail
            binding.textPrimaryTitle.text = requireContext().getString(R.string.promotions_title)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(viewModel.currentMail != PRIMARY) {
                    showPrimaryMail()
                }
                else{
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this,callback)
    }

    private fun showPrimaryMail() {
        (binding.recyclerPrimary.adapter as MailAdapter).setDummyData(DummyData.primaryMail)
        viewModel.currentMail = PRIMARY
        binding.textPrimaryTitle.text = requireContext().getString(R.string.primary_title)
        requireActivity().setDrawerIconColor(PRIMARY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val PRIMARY = 0
        const val SOCIAL = 1
        const val PROMOTIONS = 2
    }
}