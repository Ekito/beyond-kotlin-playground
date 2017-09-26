package fr.ekito.weather.json

import fr.ekito.weather.WeatherWS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Components {

    fun okHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build()
    }

    fun retrofitWS(url: String): WeatherWS {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(WeatherWS::class.java)
    }

    const val LOCAL_SERVER_URL = "http://localhost:8080"
    const val SERVER_URL = "https://my-weather-api.herokuapp.com/"
}