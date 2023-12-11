package com.example.wordappdictionary.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dictionaryapi.com/api/v3/references/collegiate/json/"
private const val API_KEY = "48d9ad21-46d0-4879-9de1-651a8ea9479a"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DictionaryApiService {
    @GET("{word}?key=${API_KEY}")
    suspend fun getWord(@Path("word") type: String) : Response<String>
}

object DictionaryApi {
    val retrofitService: DictionaryApiService by lazy {
        retrofit.create(DictionaryApiService::class.java)
    }
}