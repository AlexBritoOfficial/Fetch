package com.example.fetch.network

import com.example.fetch.data.Item
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/hiring.json")
    fun getHiringList(): Call<List<Item>>

}