package com.hafidtech.loketbus.ui.network

import com.hafidtech.loketbus.ui.model.Wrapper
import com.hafidtech.loketbus.ui.model.request.CheckoutRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.KursiResponse
import com.hafidtech.loketbus.ui.model.response.LoginResponse
import com.hafidtech.loketbus.ui.model.response.MyBookingResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
        @Field("pass") pass : String
    ) : Observable<Wrapper<LoginResponse>>

    @GET("search.php")
    fun getBusList(
        @Query("tipe") tipe : String?,
        @Query("penumpang") penumpang : String?,
        @Query("date") date : String?,
        @Query("dari") dari : String?,
        @Query("tujuan") tujuan : String?
    ) : Observable<Wrapper<ArrayList<BusResponse>>>

    @GET("kursi.php")
    fun getKursiList(
        @Query("id_bus") id_bus : String?
    ) : Observable<Wrapper<ArrayList<KursiResponse>>>

    @POST("checkout.php")
    fun setBooking (
        @Body checkoutRequest: CheckoutRequest
    ): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("checkout_update.php")
    fun setBookingUpdate(
        @Field("id_tiket") idTiket: String,
        @Field("status") statusPembayaran: String
    ): Observable<Wrapper<Any>>


    @GET("booking.php")
    fun getMyBookingList(
        @Query("id_user") idUser : String?
    ) : Observable<Wrapper<ArrayList<MyBookingResponse>>>
}