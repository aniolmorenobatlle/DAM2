package edu.uoc.android.restservice.rest.adapter

import edu.uoc.android.restservice.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseAdapter(baseUrl: String) {
    init {
        init(baseUrl)
    }

    private fun init(baseUrl: String) {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    private val client: OkHttpClient
        get() {
            val builderClientHttp = OkHttpClient().newBuilder()
            // Show HTTPS logs in dev mode
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(LEVEL_LOG)
                builderClientHttp.addInterceptor(interceptor)
            }
            return builderClientHttp.build()
        }

    fun <T> createService(_class: Class<T>): T {
        return retrofit!!.create(_class)
    }

    companion object {
        private var retrofit: Retrofit? = null
        private val LEVEL_LOG = HttpLoggingInterceptor.Level.BODY
    }
}
