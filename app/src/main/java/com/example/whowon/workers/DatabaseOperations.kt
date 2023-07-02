package com.example.whowon.workers

import androidx.work.*
import java.util.concurrent.TimeUnit

class DatabaseOperations(url: String, type: String) {

    private val _url: String = url
    private val _type = type

    private val workManager = WorkManager.getInstance()

    fun dataInsert() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = Data.Builder()
            .putString("url", _url)
            .putString("type", _type)
            .build()

        val insertRequest = OneTimeWorkRequestBuilder<DataInsertWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        workManager.enqueue(insertRequest)
    }

    fun startRefreshingRaffle() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = Data.Builder()
            .putString("url", _url)
            .putString("type", _type)
            .build()

        val refreshRequest = PeriodicWorkRequestBuilder<DataUpdateWorker>(3, TimeUnit.HOURS)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        workManager.enqueueUniquePeriodicWork("refreshRaffle", ExistingPeriodicWorkPolicy.KEEP, refreshRequest)
    }

   /* private fun stopRefreshingData() {
        workManager.cancelUniqueWork("refreshRaffle")
    }*/
}
