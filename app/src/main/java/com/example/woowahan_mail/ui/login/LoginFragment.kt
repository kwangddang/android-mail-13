package com.example.woowahan_mail.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.FragmentLoginBinding
import java.util.regex.Pattern

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    private val nameObserver: (String) -> Unit = { name ->
        if(checkNameValidation(name) || name.isBlank()){
            setDefaultNameMessage()
        }
        else{
            setErrorNameMessage()
        }
    }

    private val emailObserver: (String) -> Unit = { email ->
        if(checkEmailValidation(email) || email.isBlank()){
            setDefaultEmailMessage()
        }
        else{
            setErrorEmailMessage()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@LoginFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun checkNameValidation(name: String): Boolean{
        var numberCount = 0
        for(text in name){
            if(text in '0'..'9')
                numberCount++
        }
        return name.length > 3 && numberCount > 0
    }

    private fun checkEmailValidation(email: String): Boolean{
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun observeData(){
        viewModel.name.observe(viewLifecycleOwner,nameObserver)
        viewModel.email.observe(viewLifecycleOwner,emailObserver)
    }

    private fun setDefaultNameMessage(){
        binding.textInputLayoutLoginName.error = null
    }

    private fun setErrorNameMessage(){
        binding.textInputLayoutLoginName.error = context?.getString(R.string.name_error_message)
    }

    private fun setDefaultEmailMessage(){
        binding.textInputLayoutLoginEmail.error = null
    }

    private fun setErrorEmailMessage(){
        binding.textInputLayoutLoginEmail.error = context?.getString(R.string.email_error_message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}