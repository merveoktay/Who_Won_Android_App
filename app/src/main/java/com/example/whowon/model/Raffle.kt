package com.example.whowon.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="raffle")
data class Raffle(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nid") val nid: Int?,

    @ColumnInfo(name = "raffle_title") val title: String,

    @ColumnInfo(name = "raffle_image")
    val img : String,

    @ColumnInfo(name = "raffle_href")    val href :String,
    @ColumnInfo(name = "raffle_date") val date :String,
    @ColumnInfo(name = "raffle_gift") val gift:String,
    @ColumnInfo(name = "raffle_price") val price:String,
    @ColumnInfo(name = "raffle_detail") val detail:String?=null,
    @ColumnInfo(name = "raffle_is_follow_up") var isFollowUp:Boolean=false,
    @ColumnInfo(name="raffle_type") val raffleType:String
)
