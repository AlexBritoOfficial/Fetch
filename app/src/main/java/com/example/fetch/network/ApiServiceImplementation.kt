package com.example.fetch.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceImplementation {

  companion object {

      val BASE_URL = "https://fetch-hiring.s3.amazonaws.com"

      fun create(): Retrofit {
          return Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
      }
  }
}