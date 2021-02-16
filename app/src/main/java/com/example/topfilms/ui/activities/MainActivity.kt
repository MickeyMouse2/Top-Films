package com.example.topfilms.ui.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.topfilms.R
import com.example.topfilms.databinding.ActivityMainBinding
import com.example.topfilms.ui.adapters.ItemsAdapter
import com.example.topfilms.util.viewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val mainViewModel: MainViewModel by viewModel()

    private val userAdapter by lazy {
        ItemsAdapter(ArrayList(), (object : ItemsAdapter.Callback {
            override fun onShowLastItem() {
                mainViewModel.updateData()
            }
        }))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO:using databining for view
        val binding: ActivityMainBinding =
            DataBindingUtil
                .setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
                    adapter = userAdapter
                    viewModel = mainViewModel
                }

        binding.lifecycleOwner = this

    }
}
