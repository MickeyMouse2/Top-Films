package com.example.topfilms.ui.activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topfilms.data.objects.UserResponse
import com.example.topfilms.data.DataManager
import com.example.topfilms.data.objects.ItemsItem
import com.example.topfilms.ui.adapters.RepoListAdapter

class MainViewModel(var dataManager: DataManager) : ViewModel(), RepoListAdapter.Callback {

    private val _users = MutableLiveData<UserResponse>()
    private val _callback = MutableLiveData<RepoListAdapter.Callback>()
    private val listResponse = mutableListOf<ItemsItem>()
    private var currentPage = 1
    val users: LiveData<UserResponse>
        get() = _users
    val callback: LiveData<RepoListAdapter.Callback>
        get() = _callback

    init {
        _callback.postValue(this)
        dataManager.getUsersAsync(
                page = currentPage,
                onSuccess = {
                    listResponse.addAll(it.results)
                    _users.postValue(it)
                    Log.i("jdskfjsdl", it.toString())
                },
                onError = {
                    Log.i("jdskfjsdl", it.toString())
                })
    }

    override fun onShowLastItem() {
        if (currentPage <= _users.value!!.totalPages) {
            currentPage += 1
            dataManager.getUsersAsync(
                    page = currentPage,
                    onSuccess = {
                        listResponse.addAll(it.results)
                        it.results.clear()
                        it.results.addAll(listResponse)
                        _users.postValue(it)
//                        _users.value!!.results.addAll(it.results)
                        Log.i("jdskfjsdl", it.toString())
                    },
                    onError = {
                        Log.i("jdskfjsdl", it.toString())
                    })
        }
    }
}