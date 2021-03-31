package com.fuentescreations.signinandsignupsample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentForgotPasswordBinding
import com.fuentescreations.signinandsignupsample.ui.application.BaseFragment

class ForgotPasswordFragment : BaseFragment(R.layout.fragment_forgot_password) {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentForgotPasswordBinding.bind(view)

    }
}