package com.fuentescreations.signinandsignupsample.domain.singin

interface AuthRepo {

    suspend fun signIn(email:String,password:String):Boolean

    suspend fun signUp(email:String,password:String):Boolean
}