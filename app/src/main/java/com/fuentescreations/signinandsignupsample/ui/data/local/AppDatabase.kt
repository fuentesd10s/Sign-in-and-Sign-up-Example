package com.fuentescreations.signinandsignupsample.ui.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fuentescreations.signinandsignupsample.ui.data.models.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(mContext: Context): AppDatabase {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    mContext.applicationContext,
                    AppDatabase::class.java, "database"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE!!
        }
    }
}