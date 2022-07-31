package com.example.android.homeowrk.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {
    fun exist(id: String) = userDao.exits(id)

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also {
                    instance = it
                }
            }
    }
}