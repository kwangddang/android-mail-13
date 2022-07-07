package com.example.woowahan_mail.ui.main.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.woowahan_mail.databinding.FragmentSocialBinding
import com.example.woowahan_mail.setDrawerIconColor
import com.example.woowahan_mail.ui.main.MainActivity

class SocialFragment: Fragment() {
    private var _binding: FragmentSocialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSocialBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDrawerIconColor(MainActivity.SELECTED_SOCIAL)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}