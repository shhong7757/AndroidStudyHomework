package com.example.android.homeowrk.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE id = :id LIMIT 1)")
    fun exits(id: String): Boolean

    @Insert
    suspend fun insertUser(user: User)
}