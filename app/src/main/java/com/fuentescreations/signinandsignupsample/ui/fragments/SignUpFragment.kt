package com.fuentescreations.signinandsignupsample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentSignUpBinding
import com.fuentescreations.signinandsignupsample.ui.application.BaseFragment

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private lateinit var binding:FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSignUpBinding.bind(view)

    }
}