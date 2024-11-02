package com.hafidtech.loketbus.ui.network

import com.bagicode.bagicodebaseutils.BuildConfig
import com.bagicode.bagicodebaseutils.utils.Helpers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client : Retrofit?=null
    private var endpoint: Endpoint?=null

    companion object {
        private val mInstance : HttpClient = HttpClient()
        @Synchronized
        fun getInstance() : HttpClient {
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi() : Endpoint? {
        if (endpoint == null) {
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient() {
        val token = LoketBus.getApp().getToken()
        buildRetrofitClient(token)
    }

    private fun buildRetrofitClient(token : String) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }

        if (token != null) {
            builder.addInterceptor(getInterceptorWithHeader("Authorization", "${token}"))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL+"loketbus/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        endpoint = null
    }

    private fun getInterceptorWithHeader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return getInterceptorWithHeader(header)

    }

    private fun getInterceptorWithHeader(headers: Map<String, String>): Interceptor {
        return Interceptor {
            var original = it.request()
            var builder = original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }

            builder.method(original.method(), original.body())
            it.proceed(builder.build())
        }
    }
}