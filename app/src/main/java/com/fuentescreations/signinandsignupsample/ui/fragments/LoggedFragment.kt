package com.fuentescreations.signinandsignupsample.ui.fragments

import android.os.Bundle
import android.view.View
import com.fuentescreations.signinandsignupsample.R
import com.fuentescreations.signinandsignupsample.databinding.FragmentLoggedBinding
import com.fuentescreations.signinandsignupsample.ui.adapters.ItemUserAdapter
import com.fuentescreations.signinandsignupsample.ui.application.BaseFragment
import com.fuentescreations.signinandsignupsample.ui.data.local.AppDatabase
import com.fuentescreations.signinandsignupsample.ui.data.local.UserDao
import com.fuentescreations.signinandsignupsample.ui.data.models.UserModel

class LoggedFragment : BaseFragment(R.layout.fragment_logged) {

    private lateinit var binding: FragmentLoggedBinding
    private lateinit var adapter: ItemUserAdapter
    private lateinit var userDao: UserDao

    private val userModelList = mutableListOf<UserModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoggedBinding.bind(view)

        userDao=AppDatabase.getInstance(requireContext()).userDao()
        userModelList.addAll(userDao.getAllUsers())

        setupRv()
    }

    private fun setupRv() {
        adapter = ItemUserAdapter(userModelList)
        binding.rvUsers.adapter = adapter
    }
}