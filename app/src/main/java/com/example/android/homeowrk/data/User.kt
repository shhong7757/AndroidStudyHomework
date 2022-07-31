package com.example.android.homeowrk.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["uid", "id"], unique = true)]
)
data class User(
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "sex")
    val sex: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid: Int = 0
}