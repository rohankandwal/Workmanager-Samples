package com.itcse.workmanagersample.main.periodic_time

import com.itcse.workmanagersample.main.periodic_time.data.Analytics
import com.itcse.workmanagersample.main.periodic_time.data.ConfigResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Interface containing API calls definitions for Retrofit
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
interface RemoteApi {

    companion object {
        const val BASE_API_URL = "https://example.com/"
    }

    @get:GET("getConfig")
    val config: Call<ConfigResponse>

    @POST("reportConfig")
    fun reportConfig(@Body analytics: Analytics): Call<Void>
}