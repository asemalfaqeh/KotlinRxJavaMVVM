package com.af.ebtikartaskaf.datasource

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.jvm.Throws

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitInstance internal constructor() {

    private fun ConfigRetrofitSecureInstance() {

        val httpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        //TODO HTTP Logging Interceptor //
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.interceptors().add(httpLoggingInterceptor)
        val gson = GsonBuilder().setLenient().create()
        httpClientBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
                .connectTimeout(timeout.toLong(), TimeUnit.SECONDS) // 2 minutes
                .writeTimeout(timeout.toLong(), TimeUnit.SECONDS) // 2 minutes
                .readTimeout(timeout.toLong(), TimeUnit.SECONDS) // 2 minutes
        /////////// AF Developer //////// 28/12/2020 ////////////////////////////////
        httpClientBuilder.interceptors().add(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                var request: Request = chain.request()
                val url = request.url.newBuilder().addQueryParameter("api_key", ApiConstants.API_KEY)
                        .addQueryParameter("language", ApiConstants.APP_LANG_EN_US)
                        .build()
                request = request.newBuilder().url(url).build()
                return chain.proceed(request)
            }
        })
        retrofit = Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    val apiServicesProvider: ApiEndPoints
    // Hilt //
    @Provides
    @Singleton
    get() {
        ConfigRetrofitSecureInstance()
        return retrofit!!.create(ApiEndPoints::class.java)
    }

    companion object {
        private var retrofit: Retrofit? = null
        private const val timeout = 120
    }

}