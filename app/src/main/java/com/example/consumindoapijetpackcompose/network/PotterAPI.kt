package com.example.consumindoapijetpackcompose.network

import com.example.consumindoapijetpackcompose.data.Character
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://potterapi-fedeperin.vercel.app/pt/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retroFit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface PotterAPIService {
    @GET("characters")
    suspend fun getCharacters(): List<Character>

}

object PotterAPI {
    val retrofitService: PotterAPIService by lazy {
        retroFit.create(PotterAPIService::class.java)
    }
}

