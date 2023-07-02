package com.example.whowon.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.whowon.model.Raffle

@Dao
interface RaffleDao {

    @Insert
    fun insert( raffle: Raffle ) : Long

    @Query("select * from raffle LIMIT 8")
    fun getAll() : List<Raffle>

    @Query("select * from raffle where raffle_title like :title LIMIT 8 ")
    fun searchTitle( title: String ) : Raffle

    @Query("select * from raffle where raffle_type like :type LIMIT 8 ")
    fun searchType( type: String ) : List<Raffle>

    @Query("SELECT DISTINCT * FROM raffle WHERE raffle_is_follow_up = 1 GROUP BY raffle_title HAVING COUNT(*) >= 1")
    fun searchFollow(): List<Raffle>

    @Query("select * from raffle where nid =:nid LIMIT 8")
    fun findById( nid: Int ) : Raffle

    @Delete
    fun delete(raffle: Raffle)

    @Query("update raffle SET raffle_is_follow_up = :isFollowUp WHERE raffle_title like :title")
    fun update( title: String,isFollowUp:Boolean )

    @Update
    fun updateAll(raffle: Raffle )
}