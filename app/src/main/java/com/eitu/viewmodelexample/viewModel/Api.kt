package com.eitu.viewmodelexample.viewModel

import com.eitu.viewmodelexample.DaumSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v2/search/image")
    suspend fun getData(
        @Query("sort" ) sort  : String = "recency",
        @Query("size" ) size  : Int    = 10,
        @Query("query") query : String,
        @Query("page" ) page  : Int
    ) : Response<DaumSearchResponse>

}