package com.example.fetch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetch.data.Item
import com.example.fetch.databinding.FetchHiringListBinding
import com.example.fetch.network.ApiService
import com.example.fetch.network.ApiServiceImplementation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    lateinit var liveDatalist: MutableLiveData<List<Item>?>


    init {
        liveDatalist = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Item>?> {
        return liveDatalist
    }

     fun fetchHiringList() {
        val apiInstance = ApiServiceImplementation.create()
        val apiService = apiInstance.create(ApiService::class.java)
        val call = apiService.getHiringList()
        call.enqueue(object: Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    liveDatalist.postValue(response.body())
                } else {
                    liveDatalist.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                liveDatalist.postValue(null)
            }
        })
    }

}