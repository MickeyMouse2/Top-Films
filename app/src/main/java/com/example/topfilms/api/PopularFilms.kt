package com.example.topfilms.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.example.topfilms.data.objects.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PopularFilms {
  @GET("3/movie/popular")
  fun searchUser(
          @Query("api_key") q: String = "148a0f4f986f3ad1fb5ef21dc914d9dc",
          @Query("language") language: String = "en-US",
          @Query("page") page: Int = 1
  ): Deferred<Response<UserResponse>>

  companion object Factory {
    fun create(): PopularFilms {
      val retrofit = Retrofit.Builder()
              .baseUrl("https://api.themoviedb.org")
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(CoroutineCallAdapterFactory())
              .build()

      return retrofit.create(PopularFilms::class.java)
    }
  }
}