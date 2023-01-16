package com.bcaf.bcafretrofit.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkConfig {


    //OMDB API
    fun getInterceptor() : OkHttpClient {
        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient  = OkHttpClient.Builder().addInterceptor(logging).build()

        return  okHttpClient
    }
    fun getRetrofit() : Retrofit{
        return  Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceOMDB() = getRetrofit().create(OMDBApiInterface::class.java)


    //DUMMY API
    fun getInterceptorWithHeader() : OkHttpClient {
        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient  = OkHttpClient.Builder().addInterceptor(logging)
            .addInterceptor{
                chain ->  val request = chain.request().newBuilder()
                .addHeader("app-id", "63bce1d6ac82579158263d61")
                chain.proceed(request.build())
            }
            .build()

        return  okHttpClient
    }

    fun getRetrofitDataDummy() : Retrofit{
        return  Retrofit.Builder()
            .baseUrl("https://dummyapi.io/data/v1/")
            .client(getInterceptorWithHeader())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceDummy() = getRetrofitDataDummy().create(DummyApiInterface::class.java)
}