package com.fuentescreations.signinandsignupsample.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentSignInBinding
import com.fuentescreations.signinandsignupsample.application.BaseFragment
import com.fuentescreations.signinandsignupsample.application.ResultState
import com.fuentescreations.signinandsignupsample.data.local.AppDatabase
import com.fuentescreations.signinandsignupsample.data.local.AuthDataSource
import com.fuentescreations.signinandsignupsample.data.local.UserDao
import com.fuentescreations.signinandsignupsample.domain.singin.AuthRepoImpl
import com.fuentescreations.signinandsignupsample.viewmodels.AuthViewModel
import com.fuentescreations.signinandsignupsample.viewmodels.AuthViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val authViewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource(AppDatabase.getInstance(requireContext()).userDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        binding.btnSignIn.setOnClickListener { if (checkFields()) signIn() }
        binding.btnSignInFb.setOnClickListener { signInWithFb() }
        binding.btnSignInGoogle.setOnClickListener { signInWithGoogle() }

        binding.tvForgotPassword.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment) }
        binding.tvSignUp.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_signUpFragment) }
    }

    private fun signInWithGoogle() { mToast("Sign In with Google") }

    private fun signInWithFb() { mToast("Sign In with Facebook") }

    private fun signIn() {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        authViewModel.signIn(email, password).observe(viewLifecycleOwner, Observer {

            when (it) {
                is ResultState.Loading -> {
                    showLoading()
                }
                is ResultState.Success -> {

                    if (it.data) {
                        findNavController().navigate(R.id.action_signInFragment_to_loggedFragment)

                        rememberUser()

                        removeLoading()
                    } else {
                        mToast("Incorrect email or password")

                        removeLoading()
                    }

                }
//                is  ResultState.Failure ->{
//                    mToast("Incorrect email or password")
//
//                    removeLoading()
//                }
            }
        })
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