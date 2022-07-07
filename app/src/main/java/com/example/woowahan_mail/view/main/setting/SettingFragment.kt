package com.example.woowahan_mail.view.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.FragmentSettingBinding

class SettingFragment: Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        binding.apply {
            email = arguments?.getString(requireContext().getString(R.string.email))
            name = arguments?.getString(requireContext().getString(R.string.name))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}