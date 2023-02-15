package com.borissoto.mobiletest.framework.server

import com.borissoto.mobiletest.ui.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteConnection {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)

}