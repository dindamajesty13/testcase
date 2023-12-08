package com.majesty.testcase

import com.majesty.testcase.person.PersonModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers(
        "Accept: application/json"
    )
    @GET("person")
    fun getPerson(): Call<PersonModel?>?
}