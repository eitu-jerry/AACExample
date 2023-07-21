package com.notegg.viewmodelexample

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.*

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val context : Context get() = getApplication<Application>().applicationContext

    var query : ObservableField<String> = ObservableField("")

    fun getData() {
        //TODO - query를 검색어로 이미지 검색을 실행
        Toast.makeText(context, "검색어 : ${query.get()}", Toast.LENGTH_SHORT).show()
    }

}