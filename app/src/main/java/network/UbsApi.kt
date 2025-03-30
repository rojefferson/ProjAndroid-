package com.example.projmobile.network

import com.example.projmobile.model.Ubs
import retrofit2.Call
import retrofit2.http.GET


interface UbsApi {
    @GET("Unidades")
    fun getUbs(): Call<List<Ubs>>
}
