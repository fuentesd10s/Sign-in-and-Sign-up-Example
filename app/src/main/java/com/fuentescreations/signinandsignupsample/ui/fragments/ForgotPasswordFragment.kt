package com.fuentescreations.signinandsignupsample.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentForgotPasswordBinding
import com.fuentescreations.signinandsignupsample.ui.application.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordFragment : BaseFragment(R.layout.fragment_forgot_password) {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentForgotPasswordBinding.bind(view)

        binding.btnResetPassword.setOnClickListener { if (checkFields()) resetPassword() }
    }

    private fun resetPassword() {
        showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            Thread.sleep(2000)

            activity?.runOnUiThread {
                removeLoading()

                AlertDialog.Builder(requireContext())
                    .setTitle("Email sent.")
                    .setMessage("We sent the instruction to your email.")
                    .setPositiveButton("Ok"){ d, _-> d.dismiss()}
                    .show()
            }
        }
    }

    private fun checkFields(): Boolean {
        binding.textInputLayoutEmail.error = null

        val email = binding.etEmail.text.toString()

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Invalid email"
            return false
        }

        return true
    }
    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnResetPassword.visibility = View.GONE
    }

    private fun removeLoading() {
        binding.progressBar.visibility = View.GONE
        binding.btnResetPassword.visibility = View.VISIBLE
    }
}