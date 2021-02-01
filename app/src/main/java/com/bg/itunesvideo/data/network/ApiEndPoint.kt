package com.bg.itunesvideo.data.network


import com.bg.imusicplayer.data.network.NetworkConnectionInterceptor
import com.bg.itunesvideo.data.model.objects.VideoListObj
import com.bg.itunesvideo.data.utils.AppConstants.APP_URL
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiEndPoint {



 @GET("search?term=Michael+jackson&media=musicVideo")
    suspend fun fetchVideoSongs() : Response<VideoListObj>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : ApiEndPoint {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .build()
                .create(ApiEndPoint::class.java)
        }
    }

}

