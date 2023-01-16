package com.bcaf.bcafretrofit.service

import com.bcaf.bcafretrofit.PostDataDummy
import com.bcaf.bcafretrofit.model.PostDummyData
import com.bcaf.bcafretrofit.model.ResponsePostDataDummy
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DummyApiInterface {

//    @Headers("app-id:63bce1d6ac82579158263d61")
    @POST("post/create")
    fun postData(@Body dummyData: PostDummyData): Call<ResponsePostDataDummy>
}