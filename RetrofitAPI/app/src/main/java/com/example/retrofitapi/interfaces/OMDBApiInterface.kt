package com.example.retrofitapi.interfaces

import com.example.retrofitapi.model.OMDBResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OMDBApiInterface {
    //http://www.omdbapi.com/?apikey=8a97b2ee&s=Terminator

    @GET("?apikey=8a97b2ee")
    fun searchMovie(@Query("s") search: String): Call<OMDBResponse>

    @GET("?apikey=8a97b2ee")
    fun searchMoviebyId(@Query("i") search: String): Call<OMDBResponse>

    @GET("?apikey=8a97b2ee&s={title}")
    fun searchMovieV2(@Path("title") title: String): Call<OMDBResponse>
}