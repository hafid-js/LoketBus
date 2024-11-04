package com.hafidtech.loketbus.ui.network

import com.hafidtech.loketbus.ui.model.Wrapper
import com.hafidtech.loketbus.ui.model.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Endpoint {

    @FormUrlEncoded
    @POST("register.php")
    fun setRegister(
        @Field("email") email : String,
        @Field("pass") pass : String,
        @Field("username") username : String
    ) : Observable<Wrapper<Any>>

    @FormUrlEncoded
    @POST("login.php")
    fun setLogin(
        @Field("email") email : String,
        @Field("pass") pass : String,
    ) : Observable<Wrapper<LoginResponse>>
}