package com.example.android.homeowrk.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowerDao {
    @Query("SELECT * FROM flowers")
    fun getFlowers(): Flow<List<Flower>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(flowers: List<Flower>)
}