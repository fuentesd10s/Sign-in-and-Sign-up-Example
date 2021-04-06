package com.fuentescreations.signinandsignupsample.data.local

import com.fuentescreations.signinandsignupsample.data.models.UserModel

class AuthDataSource(private val userDao: UserDao) {
     fun signIn(email: String, password: String): Boolean {

        Thread.sleep(2000)

        return userDao.getUser(email, password) != null
    }

     fun signUp(email:String,password:String):Boolean{
        Thread.sleep(2000)

        userDao.insertUser(UserModel(0, email, password))

        return true
    }
}