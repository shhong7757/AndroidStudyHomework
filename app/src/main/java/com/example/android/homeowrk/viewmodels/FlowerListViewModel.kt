package com.example.android.homeowrk.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.android.homeowrk.data.Flower
import com.example.android.homeowrk.data.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlowerListViewModel @Inject constructor(flowerRepository: FlowerRepository) : ViewModel() {
    val flowers: LiveData<List<Flower>> =
        flowerRepository.getFlowers().asLiveData()
}