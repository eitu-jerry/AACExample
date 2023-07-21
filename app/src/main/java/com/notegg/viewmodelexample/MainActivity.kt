package com.notegg.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.notegg.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val myViewModel : MyViewModel by viewModels()

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
    }

}