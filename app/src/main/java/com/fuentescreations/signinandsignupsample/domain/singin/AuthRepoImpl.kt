package com.fuentescreations.signinandsignupsample.domain.singin

import com.fuentescreations.signinandsignupsample.data.local.AuthDataSource

class AuthRepoImpl(private val dataSource:AuthDataSource) : AuthRepo {

    override suspend fun signIn(email: String, password: String): Boolean = dataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String): Boolean = dataSource.signUp(email,password)
}