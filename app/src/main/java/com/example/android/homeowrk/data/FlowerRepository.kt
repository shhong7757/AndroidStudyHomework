package com.example.android.homeowrk.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlowerRepository @Inject constructor(private val flowerDao: FlowerDao) {
    fun getFlowers() = flowerDao.getFlowers()

    companion object {
        @Volatile
        private var instance: FlowerRepository? = null

        fun getInstance(flowerDao: FlowerDao) =
            instance ?: synchronized(this) {
                instance ?: FlowerRepository(flowerDao).also {
                    instance = it
                }
            }
    }

}