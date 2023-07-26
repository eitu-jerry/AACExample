package com.notegg.viewmodelexample.viewModel

import com.notegg.viewmodelexample.DaumSearchResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class MyRetrofit {

    private val api : API
    private val baseUrl = "https://dapi.kakao.com/"
    private val kakaoAK = "8c6490d79ed7bebc0977b541617cfcbex"

    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                //카카오 RestAPI 키를 공통 헤더로써 호출에 적용
                requestBuilder.header("Authorization", "KakaoAK $kakaoAK")
                chain.proceed(requestBuilder.build())
            }
            .build()

        api = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }

//    private fun<T> returnResult(response: Response<T>) : Result<T> {
//        return try {
//            if (response.code() in 200 until 300) {
//                val body = response.body()
//                if (body != null) {
//                    Result.Success(body)
//                }
//                else {
//                    Result.Error(Exception("body is null"))
//                }
//            }
//            else {
//                Result.Error(Exception("${response.code()} : ${response.message()}"))
//            }
//        } catch (e : Exception) {
//            Result.Error(e)
//        }
//    }
//
//    suspend fun getData(query: String, page: Int = 1) : Result<DaumSearchResponse> {
//        return returnResult(api.getData(query = query, page = page))
//    }
//
    interface API {

        @GET("v2/search/image")
        suspend fun getData(
            @Query("sort" ) sort  : String = "recency",
            @Query("size" ) size  : Int    = 10,
            @Query("query") query : String,
            @Query("page" ) page  : Int
        ) : Response<DaumSearchResponse>

    }

}

//sealed class Result<out T> {
//    data class Success<out T>(val data : T) : Result<T>()
//    data class Error(val exception : Exception) : Result<Nothing>()
//}