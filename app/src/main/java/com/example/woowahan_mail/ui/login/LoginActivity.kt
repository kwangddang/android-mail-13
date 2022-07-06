package com.example.woowahan_mail.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.woowahan_mail.R
import com.example.woowahan_mail.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    private val nameObserver: (String) -> Unit = { name ->
        if(checkNameValidation(name)){
            setDefaultNameMessage()
            viewModel.nameSuccess = true
        }
        else if(name.isBlank()){
            setDefaultNameMessage()
            viewModel.nameSuccess = false
        }
        else{
            setErrorNameMessage()
            viewModel.nameSuccess = false
        }
        checkComplete()
    }

    private val emailObserver: (String) -> Unit = { email ->
        if(checkEmailValidation(email)){
            setDefaultEmailMessage()
            viewModel.emailSuccess = true
        }
        else if(email.isBlank()){
            setDefaultNameMessage()
            viewModel.emailSuccess = false
        }
        else{
            setErrorEmailMessage()
            viewModel.emailSuccess = false
        }
        checkComplete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDataBinding()
        observeData()
        setOnClickListeners()
    }

    private fun initDataBinding() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@LoginActivity
        }
    }

    private fun observeData(){
        viewModel.name.observe(this,nameObserver)
        viewModel.email.observe(this,emailObserver)
    }

    private val btnNextOnClickListener: (View) -> Unit = {

    }

    private fun setOnClickListeners(){
        binding.btnLoginNext.setOnClickListener(btnNextOnClickListener)
    }

    private fun checkComplete(){
        binding.btnLoginNext.isEnabled = viewModel.nameSuccess && viewModel.emailSuccess
    }

    private fun checkNameValidation(name: String): Boolean{
        var numberCount = 0
        var englishCount = 0
        for(text in name){
            if(text in '0'..'9')
                numberCount++
            else if (text in 'A'..'z')
                englishCount++
        }
        return name.length > 3 && numberCount > 0 && englishCount > 0
    }

    private fun checkEmailValidation(email: String): Boolean{
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun setDefaultNameMessage(){
        binding.textInputLayoutLoginName.error = null
    }

    private fun setErrorNameMessage(){
        binding.textInputLayoutLoginName.error = this.getString(R.string.name_error_message)
    }

    private fun setDefaultEmailMessage(){
        binding.textInputLayoutLoginEmail.error = null
    }

    private fun setErrorEmailMessage(){
        binding.textInputLayoutLoginEmail.error = this.getString(R.string.email_error_message)
    }
}