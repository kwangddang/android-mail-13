package com.example.woowahan_mail.view.main.mail.primary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.woowahan_mail.data.DummyData
import com.example.woowahan_mail.databinding.FragmentPrimaryBinding
import com.example.woowahan_mail.view.setDrawerIconColor
import com.example.woowahan_mail.view.main.MainActivity
import com.example.woowahan_mail.view.main.mail.MailAdapter

class PrimaryFragment: Fragment() {
    private var _binding: FragmentPrimaryBinding? = null
    private val binding get() = _binding!!

    private val adapter: MailAdapter by lazy{ MailAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrimaryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDrawerIconColor(MainActivity.SELECTED_PRIMARY)
        Log.d("Test","onCreate")
        setAdapter()
    }

    private fun setAdapter(){
        adapter.setDummyData(DummyData.primaryMail)
        binding.recyclerPrimary.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}