package com.fuentescreations.signinandsignupsample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.fuentescreations.signinandsignupsample.application.ResultState
import com.fuentescreations.signinandsignupsample.domain.singin.AuthRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class AuthViewModel(private val repo: AuthRepo) : ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(ResultState.Loading())

        emit(ResultState.Success(repo.signIn(email, password)))
    }

    fun signUp(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(ResultState.Loading())

        emit(ResultState.Success(repo.signUp(email, password)))
    }
}

class AuthViewModelFactory(private val repo: AuthRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repo) as T
    }
}