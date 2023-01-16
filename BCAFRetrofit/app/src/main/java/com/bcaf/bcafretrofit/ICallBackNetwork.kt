package com.bcaf.bcafretrofit

import com.bcaf.bcafretrofit.model.OMDBDetailResponse
import com.bcaf.bcafretrofit.model.ResponsePostDataDummy
import com.bcaf.bcafretrofit.model.SearchItem

interface ICallBackNetwork {

    fun onFinish(data :List<SearchItem>)
    fun onFinishDetail(data : OMDBDetailResponse)
    fun onFailed()
}