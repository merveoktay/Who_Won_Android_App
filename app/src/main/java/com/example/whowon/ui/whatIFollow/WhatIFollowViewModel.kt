package com.example.whowon.ui.whatIFollow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whowon.MainActivity
import com.example.whowon.configs.DatabaseSingleton
import com.example.whowon.model.Raffle

class WhatIFollowViewModel : ViewModel() {
    private val database = DatabaseSingleton.getInstance(MainActivity.mainContext)
    private val dao = database.raffleDao()

    private val _raffleList = MutableLiveData<List<Raffle>>()
    val raffleList: LiveData<List<Raffle>> = _raffleList

    init {
        loadRaffles()
    }

    private fun loadRaffles() {
        val runnable = Runnable {
            val raffles = dao.searchFollow()
            _raffleList.postValue(raffles)
        }
        Thread(runnable).start()
    }
}