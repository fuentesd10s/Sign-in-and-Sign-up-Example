package com.fuentescreations.signinandsignupsample.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentSignUpBinding
import com.fuentescreations.signinandsignupsample.application.BaseFragment
import com.fuentescreations.signinandsignupsample.application.ResultState
import com.fuentescreations.signinandsignupsample.data.local.AppDatabase
import com.fuentescreations.signinandsignupsample.data.local.AuthDataSource
import com.fuentescreations.signinandsignupsample.data.local.UserDao
import com.fuentescreations.signinandsignupsample.data.models.UserModel
import com.fuentescreations.signinandsignupsample.domain.singin.AuthRepoImpl
import com.fuentescreations.signinandsignupsample.viewmodels.AuthViewModel
import com.fuentescreations.signinandsignupsample.viewmodels.AuthViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    private val authViewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource(AppDatabase.getInstance(requireContext()).userDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        binding.btnSignUp.setOnClickListener { if (checkFields()) signUp() }
        binding.tvSignIn.setOnClickListener { activity?.onBackPressed() }
    }

    private fun signUp() {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        registerUser(email, password)
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

    private fun registerUser(email: String, password: String) {

        authViewModel.signUp(email, password).observe(viewLifecycleOwner, Observer {

            when (it) {
                is ResultState.Loading -> {
                    showLoading()
                }
                is ResultState.Success -> {

                    if (it.data) {
                        activity?.onBackPressed()

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