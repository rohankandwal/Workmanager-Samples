package com.itcse.workmanagersample.main

import android.app.Application
import android.widget.Toast
import androidx.work.Configuration
import androidx.work.WorkManager
import com.itcse.workmanagersample.BuildConfig
import com.itcse.workmanagersample.main.dagger_injection.di.DaggerSampleComponent
import com.itcse.workmanagersample.main.dagger_injection.factory.SampleWorkerFactory
import com.itcse.workmanagersample.main.periodic_time.RemoteApi
import com.itcse.workmanagersample.main.periodic_time.data.Analytics
import com.itcse.workmanagersample.main.periodic_time.netwok.ApiHelper
import com.itcse.workmanagersample.main.periodic_time.netwok.MockClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Application class used to load the default configurations used in the app.
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
class SampleApplication : Application() {

    companion object {
        lateinit var thisApplication: SampleApplication
            private set
    }

    private lateinit var remoteApi: RemoteApi

    lateinit var apiHelper: ApiHelper
        private set


    override fun onCreate() {
        super.onCreate()

        thisApplication = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setUpApi()
    }

    /**
     * Mock API to emulate analytics
     */
    private fun setUpApi() {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json").build()
            chain.proceed(request)
        }

        // Interceptor used for logging the API calls
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logger)
            clientBuilder.addInterceptor(MockClient(applicationContext))
        }

        // Setting up Retrofit for making API calls
        remoteApi = Retrofit.Builder()
            .baseUrl(RemoteApi.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
            .create(RemoteApi::class.java)

        // Initializing Api helper used for making API calls.
        apiHelper = ApiHelper(remoteApi)
    }
}