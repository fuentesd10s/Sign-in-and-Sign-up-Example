package com.fuentescreations.signinandsignupsample.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fuentescreations.signinandsignupsample.R

open class BaseFragment(private val mView: Int) : Fragment(mView) {
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isUserLogged()
    }

    override fun onStart() {
        super.onStart()

        isUserLogged()
    }

    fun mToast(message: String, duration: Int = Toast.LENGTH_LONG) {
        if (this::toast.isInitialized) {
            toast.cancel()
        }

        toast = Toast.makeText(requireContext(), message, duration)

        toast.show()
    }

    private fun isUserLogged() {
        val mPref = requireActivity().getSharedPreferences("GENERAL", 0)

        val isLogged = mPref.getBoolean("USER_LOGGED", false)

        if (isLogged && mView == R.layout.fragment_sign_in) {
            findNavController().navigate(R.id.action_signInFragment_to_loggedFragment)
        }
        if (isLogged && mView == R.layout.fragment_sign_up) {
            findNavController().navigate(R.id.action_signUpFragment_to_loggedFragment)
        }
        if (!isLogged && mView == R.layout.fragment_logged) {
            findNavController().navigate(R.id.action_loggedFragment_to_signInFragment)
        }
    }

    fun rememberUser() {
        val mPref = requireActivity().getSharedPreferences("GENERAL", 0)

        val editor = mPref.edit()

        editor.putBoolean("USER_LOGGED", true)

        editor.apply()
    }
}