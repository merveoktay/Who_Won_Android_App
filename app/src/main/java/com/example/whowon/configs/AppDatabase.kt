package com.example.whowon.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whowon.dao.RaffleDao
import com.example.whowon.model.Raffle

@Database(entities = [Raffle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun raffleDao() : RaffleDao


}