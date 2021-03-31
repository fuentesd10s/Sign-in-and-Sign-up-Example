package com.fuentescreations.signinandsignupsample.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.MainThread
import androidx.navigation.fragment.findNavController
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentSignInBinding
import com.fuentescreations.signinandsignupsample.ui.application.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        binding.btnSignIn.setOnClickListener { if (checkFields()) signIn() }
        binding.btnSignInFb.setOnClickListener { signInWithFb() }
        binding.btnSignInGoogle.setOnClickListener { signInWithGoogle() }

        binding.tvForgotPassword.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment) }
        binding.tvSignUp.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_signUpFragment) }
    }

    private fun signInWithGoogle() {
        mToast("Sign In with Google")
    }

    private fun signInWithFb() {
        mToast("Sign In with Facebook")
    }

    private fun signIn() {
        showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            Thread.sleep(2000)

            (requireContext() as Activity).runOnUiThread {
                removeLoading()

                mToast("Sign In successfully!")
            }
        }
    }

    private fun checkFields(): Boolean {
        binding.textInputLayoutEmail.error = null
        binding.textInputLayoutPassword.error = null

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Invalid email"
            return false
        }
        if (password.length < 6) {
            binding.textInputLayoutPassword.error = "Invalid password"
            return false
        }

        return true
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSignIn.visibility = View.GONE

        binding.btnSignIn.isEnabled = false
        binding.btnSignInGoogle.isEnabled = false
        binding.btnSignInFb.isEnabled = false
        binding.tvSignUp.isEnabled = false
        binding.tvForgotPassword.isEnabled = false
    }

    private fun removeLoading() {
        binding.progressBar.visibility = View.GONE
        binding.btnSignIn.visibility = View.VISIBLE

        binding.btnSignIn.isEnabled = true
        binding.btnSignInGoogle.isEnabled = true
        binding.btnSignInFb.isEnabled = true
        binding.tvSignUp.isEnabled = true
        binding.tvForgotPassword.isEnabled = true
    }
}