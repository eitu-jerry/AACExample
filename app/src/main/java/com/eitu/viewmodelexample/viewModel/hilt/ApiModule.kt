package com.eitu.viewmodelexample.viewModel.hilt

import com.eitu.viewmodelexample.viewModel.Api
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideBaseUrl() = "https://dapi.kakao.com/"

    @Provides
    fun provideKakaoAK() = "fa9c16d625255d8eda8ba39d5fc67ec7"

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            //카카오 RestAPI 키를 공통 헤더로써 호출에 적용
            requestBuilder.header("Authorization", "KakaoAK ${provideKakaoAK()}")
            chain.proceed(requestBuilder.build())
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(provideBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit) : Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api : Api) : Repository {
        return Repository(api)
    }

}