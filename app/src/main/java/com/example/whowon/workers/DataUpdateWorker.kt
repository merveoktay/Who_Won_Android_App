package com.example.whowon.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.whowon.configs.DatabaseSingleton
import com.example.whowon.model.Raffle
import com.example.whowon.services.Result


class DataUpdateWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val url = inputData.getString("url")
        val type = inputData.getString("type")

        val result = Result()
        val database = DatabaseSingleton.getInstance(applicationContext)
        val dao = database.raffleDao()

        val run = Runnable {
            val list = result.raffle(url!!, type!!)
            for (item in list) {
                val raffle = Raffle(
                    item.nid,
                    item.title,
                    item.img,
                    item.href,
                    item.date,
                    item.gift,
                    item.price,
                    item.detail,
                    item.isFollowUp,
                    type
                )
                dao.updateAll(raffle)
            }
        }
        Thread(run).start()
        return Result.success()
    }
}