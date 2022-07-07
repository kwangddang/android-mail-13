package com.example.woowahan_mail.view.main.mail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.FragmentMailBinding
import com.example.woowahan_mail.view.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MailFragment: Fragment() {
    private var _binding: FragmentMailBinding? = null
    private val binding get() = _binding!!

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
        (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation_main).
            menu.findItem(R.id.item_main_menu_mail).isChecked = true

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}