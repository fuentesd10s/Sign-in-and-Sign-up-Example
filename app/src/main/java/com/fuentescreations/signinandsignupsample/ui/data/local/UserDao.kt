package com.fuentescreations.signinandsignupsample.ui.data.local

import androidx.room.*
import com.fuentescreations.signinandsignupsample.ui.data.models.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserModel WHERE email IS :email AND password IS :password")
    fun getUser(email: String,password: String): UserModel

    @Query("SELECT * FROM UserModel")
    fun getAllUsers(): List<UserModel>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userModel: UserModel)
}