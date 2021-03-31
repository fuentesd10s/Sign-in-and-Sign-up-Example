package com.fuentescreations.signinandsignupsample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentSignUpBinding
import com.fuentescreations.signinandsignupsample.ui.application.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        binding.btnSignUp.setOnClickListener { if (checkFields()) signUp() }
        binding.tvSignIn.setOnClickListener { activity?.onBackPressed() }

    }

    private fun signUp() {
        showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            Thread.sleep(2000)

            activity?.runOnUiThread {
                removeLoading()

                mToast("Sign Up successfully!")
            }
        }
    }

    private fun checkFields(): Boolean {
        binding.textInputLayoutEmail.error = null
        binding.textInputLayoutPassword.error = null
        binding.textInputLayoutPasswordConfirm.error = null

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordConfirm = binding.etPasswordConfirm.text.toString()

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Invalid email"
            return false
        }
        if (password.length < 6) {
            binding.textInputLayoutPassword.error = "Invalid password"
            return false
        }
        if (passwordConfirm != password) {
            binding.textInputLayoutPasswordConfirm.error = "Password doesn't match"
            return false
        }

        return true
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSignUp.visibility = View.GONE
        binding.tvSignIn.isEnabled = false
    }

    private fun removeLoading() {
        binding.progressBar.visibility = View.GONE
        binding.btnSignUp.visibility = View.VISIBLE
        binding.tvSignIn.isEnabled = true
    }
}