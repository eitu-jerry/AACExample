package com.eitu.viewmodelexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.eitu.viewmodelexample.viewModel.MyViewModel
import com.eitu.viewmodelexample.R
import com.eitu.viewmodelexample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val myViewModel : MyViewModel by viewModels()

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
            adapterSearch.resetList(it)
        }
    }

}