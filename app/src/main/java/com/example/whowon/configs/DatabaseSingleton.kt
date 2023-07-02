package com.example.whowon.configs

import android.content.Context
import androidx.room.Room

object DatabaseSingleton {
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appDataBase"
            ).build()
        }
        return instance as AppDatabase
    }
}