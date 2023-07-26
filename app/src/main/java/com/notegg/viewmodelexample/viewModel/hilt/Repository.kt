package com.notegg.viewmodelexample.viewModel.hilt

import com.notegg.viewmodelexample.DaumSearchResponse
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api : Api) {

    private fun<T> returnResult(response: Response<T>) : Result<T> {
        return try {
            if (response.code() in 200 until 300) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                }
                else {
                    Result.Error(Exception("body is null"))
                }
            }
            else {
                Result.Error(Exception("${response.code()} : ${response.errorBody()?.string()}"))
            }
        } catch (e : Exception) {
            Result.Error(e)
        }
    }

    suspend fun getData(query: String, page: Int) : Result<DaumSearchResponse> {
        return returnResult(api.getData(query = query, page = page))
    }

}

sealed class Result<out T> {
    data class Success<out T>(val data : T) : Result<T>()
    data class Error(val exception : Exception) : Result<Nothing>()
}