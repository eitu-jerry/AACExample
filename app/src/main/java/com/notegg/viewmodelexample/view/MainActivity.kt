package com.notegg.viewmodelexample.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.notegg.viewmodelexample.viewModel.MyViewModel
import com.notegg.viewmodelexample.R
import com.notegg.viewmodelexample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    val myViewModel : MyViewModel by viewModels()

    private val adapterSearch = AdapterSearch()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            myViewModel = this@MainActivity.myViewModel
            lifecycleOwner = this@MainActivity
        }
        binding.query.setOnEditorActionListener { _, _, _ ->
            myViewModel.getData()
            true
        }
        binding.searchResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterSearch
        }
        myViewModel.searchResult.observe(this) {
            Toast.makeText(applicationContext, "검색 결과 ${it.size} 개", Toast.LENGTH_SHORT).show()
            adapterSearch.resetList(it)
        }
    }

}