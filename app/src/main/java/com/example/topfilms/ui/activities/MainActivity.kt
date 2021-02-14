package com.example.topfilms.ui.activities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import com.example.topfilms.R
import com.example.topfilms.databinding.ActivityMainBinding
import com.example.topfilms.ui.adapters.RepoListAdapter
import com.example.topfilms.util.viewModel


class MainActivity : AppCompatActivity(), KodeinAware{

    override val kodein by kodein()

    private val mainViewModel:MainViewModel by viewModel()

    private val userAdapter by lazy {
        RepoListAdapter(ArrayList(), (object: RepoListAdapter.Callback{
            override fun onShowLastItem() {

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
