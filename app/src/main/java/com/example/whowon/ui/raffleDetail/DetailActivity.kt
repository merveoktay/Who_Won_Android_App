package com.example.whowon.ui.raffleDetail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.whowon.MainActivity
import com.example.whowon.R
import com.example.whowon.configs.DatabaseSingleton
import com.example.whowon.services.Result
import com.example.whowon.utils.Util
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    val raffle= Util._raffle
    private lateinit var raffleDetailTitle:TextView
    private lateinit var raffleDetail:TextView
    private lateinit var raffleDate:TextView
    private lateinit var startingDate:TextView
    private lateinit var endDate:TextView
    private lateinit var minSpendAmount:TextView
    private lateinit var totalGiftValue:TextView
    private lateinit var totalNumberOfGifts:TextView
    private lateinit var listingDate:TextView
    private lateinit var raffleDetailImage:ImageView
    private lateinit var fallowButton:Button
    private val result= Result()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        init()

        lifecycleScope.launch {
            val raffleDetails = result.fetchRaffleDetail(raffle.href)
            raffleDetail.text=raffleDetails["raffleDetail"]
            raffleDate.text=raffleDetails["raffleDate"]
            startingDate.text=raffleDetails["startingDate"]
            endDate.text=raffleDetails["endDate"]
            minSpendAmount.text=raffleDetails["minSpendAmount"]
            totalGiftValue.text=raffleDetails["totalGiftValue"]
            totalNumberOfGifts.text=raffleDetails["totalNumberOfGifts"]
            listingDate.text=raffleDetails["listingDate"]
        }
        Log.d("Follow",raffle.isFollowUp.toString())
        if(raffle.isFollowUp) fallowButton.text="UNFOLLOW"
        else fallowButton.text="FOLLOW"

        Glide.with(this)
            .load(raffle.img)
            .into(raffleDetailImage)

        raffleDetailTitle.text=raffle.title

        fallowButton.setOnClickListener {
            if (fallowButton.text=="FOLLOW") {
                fallowButton.text = "UNFOLLOW"
                raffle.isFollowUp = true
                updateRaffles(raffle.title,true)
            } else {
                fallowButton.text="FOLLOW"
                raffle.isFollowUp = false
                updateRaffles(raffle.title,false)
            }
        }
    }

    private fun init(){
        raffleDetailTitle=findViewById(R.id.raffleDetailTitleTextView)
        raffleDetail=findViewById(R.id.raffleDetailTextView)
        raffleDate=findViewById(R.id.raffleDateTextView)
        startingDate=findViewById(R.id.startingDateTextView)
        endDate=findViewById(R.id.endDateTextView)
        minSpendAmount=findViewById(R.id.minSpendAmountTextView)
        totalGiftValue=findViewById(R.id.totalGiftValueTextView)
        totalNumberOfGifts=findViewById(R.id.totalNumberOfGiftsTextView)
        listingDate=findViewById(R.id.listingDateTextView)
        fallowButton=findViewById(R.id.fallowButton)
        raffleDetailImage=findViewById(R.id.raffleDetailImageView)
    }

    private fun updateRaffles(title:String,isFollowUp:Boolean) {
        val database = DatabaseSingleton.getInstance(this)
        val dao = database.raffleDao()
        val runnable = Runnable {
            dao.update(title,isFollowUp)
        }
        Thread(runnable).start()
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

        val intent = Intent (this , MainActivity::class.java )

        startActivity(intent)
        super.onBackPressed()
    }
}