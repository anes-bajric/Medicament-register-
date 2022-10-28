package com.yourcompany.medicamentregister

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("test/medicaments")
    fun getData(): Call<List<MyDataItem>>

    @GET("test/categories")
    fun getAdditionalData(): Call<List<MyAdditionalDataItem>>
}
