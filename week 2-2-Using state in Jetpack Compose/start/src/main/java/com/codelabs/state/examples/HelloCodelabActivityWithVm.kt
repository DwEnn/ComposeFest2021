package com.codelabs.state.examples

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class HelloCodelabActivityWithVm: AppCompatActivity() {

    private val viewModel by viewModels<HelloCodelabViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}