package com.fuentescreations.signinandsignupsample.ui.fragments

import android.os.Bundle
import android.view.View
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentLoggedBinding
import com.fuentescreations.signinandsignupsample.adapters.ItemUserAdapter
import com.fuentescreations.signinandsignupsample.application.BaseFragment
import com.fuentescreations.signinandsignupsample.data.local.AppDatabase
import com.fuentescreations.signinandsignupsample.data.local.UserDao
import com.fuentescreations.signinandsignupsample.data.models.UserModel

class LoggedFragment : BaseFragment(R.layout.fragment_logged) {

    private lateinit var binding: FragmentLoggedBinding
    private lateinit var adapter: ItemUserAdapter
    private lateinit var userDao: UserDao

    private val userModelList = mutableListOf<UserModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoggedBinding.bind(view)

        binding.btnLogout.setOnClickListener { logout() }

        userDao = AppDatabase.getInstance(requireContext()).userDao()
        userModelList.addAll(userDao.getAllUsers())

        setupRv()
    }

    private fun setupRv() {
        adapter = ItemUserAdapter(userModelList)
        binding.rvUsers.adapter = adapter
    }

    private fun logout() {
        val mPref = requireActivity().getSharedPreferences("GENERAL", 0)

        val editor = mPref.edit()

        editor.putBoolean("USER_LOGGED", false)

        editor.apply()

        requireActivity().onBackPressed()
    }
}