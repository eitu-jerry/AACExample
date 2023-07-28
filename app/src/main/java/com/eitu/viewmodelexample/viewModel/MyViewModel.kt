package com.eitu.viewmodelexample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eitu.viewmodelexample.DaumSearchDocument
import com.eitu.viewmodelexample.viewModel.hilt.Repository
import com.eitu.viewmodelexample.viewModel.hilt.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val query = MutableLiveData("")

    val searchResult = MutableLiveData<List<DaumSearchDocument>>(emptyList())

    fun getData() {
        //TODO - query를 검색어로 이미지 검색을 실행
        val queryString : String? = query.value
        if (queryString != null) {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    repository.getData(queryString, 1)
                }
                when(response) {
                    is Result.Success -> {
                        searchResult.value = response.data.documents
                    }
                    is Result.Error -> {
                        response.exception.printStackTrace()
                    }
                    else -> {

                    }
                }
            }

        }
    }

}