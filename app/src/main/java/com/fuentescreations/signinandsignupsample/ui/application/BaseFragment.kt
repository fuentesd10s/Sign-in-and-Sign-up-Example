package com.fuentescreations.signinandsignupsample.ui.application

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment(view: Int) : Fragment(view) {
    private lateinit var toast: Toast

    fun mToast(message: String, duration: Int = Toast.LENGTH_LONG) {
        if (this::toast.isInitialized) {
            toast.cancel()
        }

        toast = Toast.makeText(requireContext(), message, duration)

        toast.show()
    }
}