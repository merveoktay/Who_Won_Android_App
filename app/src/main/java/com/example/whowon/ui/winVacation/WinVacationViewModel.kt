package com.example.whowon.ui.winVacation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whowon.MainActivity
import com.example.whowon.configs.DatabaseSingleton
import com.example.whowon.model.Raffle

class WinVacationViewModel : ViewModel() {
    private val database = DatabaseSingleton.getInstance(MainActivity.mainContext)
    private val dao = database.raffleDao()

    private val _raffleList = MutableLiveData<List<Raffle>>()

    val raffleList: LiveData<List<Raffle>>
        get() = _raffleList

    init {
        loadRaffles()
    }

    private fun loadRaffles() {
        val run = Runnable {
            val raffles = dao.searchType("WinVacation")
            _raffleList.postValue(raffles)
        }
        Thread(run).start()
    }
}