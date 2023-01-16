package com.example.retrofitapi.interfaces

import com.example.retrofitapi.model.SearchItem

interface CallBackNetwork {
    fun onFinish(data: List<SearchItem>)
    fun onFailed()
}