package com.example.whowon.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.whowon.configs.DatabaseSingleton
import com.example.whowon.model.Raffle

class DataInsertWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val url = inputData.getString("url")
        val type = inputData.getString("type")

        if (url.isNullOrEmpty() || type.isNullOrEmpty()) {
            return Result.failure()
        }

        val result = doDataInsert(url, type)

        return if (result) {
            Result.success()
        } else {
            Result.failure()
        }
    }

    private fun doDataInsert(url: String, type: String): Boolean {
        val result = com.example.whowon.services.Result()
        val database = DatabaseSingleton.getInstance(applicationContext)
        val dao = database.raffleDao()

        val list = result.raffle(url, type)
        for (item in list) {
            val raffle = Raffle(item.nid, item.title, item.img, item.href, item.date, item.gift, item.price, item.detail, item.isFollowUp, type)
            dao.insert(raffle)
        }

        return true
    }
}
