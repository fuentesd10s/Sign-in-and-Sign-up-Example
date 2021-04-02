package com.fuentescreations.signinandsignupsample.ui.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,

    @ColumnInfo(name = "email")
    val email: String = "",

    @ColumnInfo(name = "password")
    val password: String = ""
)
