package com.example.sixth_home_task.data.network

import com.example.sixth_home_task.domain.models.BankATM
import com.example.sixth_home_task.domain.models.BankFilial
import com.example.sixth_home_task.domain.models.BankInfobox
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://belarusbank.by/api/"

interface BankApiService {
    @GET("atm?")
    fun getATM(@Query("city") city: String): Observable<List<BankATM>>

    @GET("infobox?")
    fun getInfobox(@Query("city") city: String): Observable<List<BankInfobox>>

    @GET("filials_info?")
    fun getFilial(@Query("city") city: String): Observable<List<BankFilial>>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

object BankApi {
    val retrofitService: BankApiService by lazy {
        retrofit.create(BankApiService::class.java)
    }
}