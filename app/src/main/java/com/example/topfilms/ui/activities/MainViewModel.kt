package com.example.topfilms.ui.activities

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topfilms.data.objects.UserResponse
import com.example.topfilms.data.DataManager
import com.example.topfilms.data.objects.ItemsItemViewModel
import com.example.topfilms.ui.adapters.ItemsAdapter

class MainViewModel(var dataManager: DataManager) : ViewModel(){

    private val _users = MutableLiveData<UserResponse>()
    public var _internetConnection: ObservableField<Boolean> = ObservableField();
    private val listResponse = mutableListOf<ItemsItemViewModel>()
    private var currentPage = 1
    val users: LiveData<UserResponse>
        get() = _users


    init {
        response()
    }

    fun response(){
        dataManager.getUsersAsync(
            page = currentPage,
            onSuccess = {
                listResponse.addAll(it.results)
                _users.postValue(it)
                _internetConnection.set(true)
                Log.i("jdskfjsdl", it.toString())
            },
            onError = {
                _internetConnection.set(false)
                Log.i("jdskfjsdl", it.toString())
            })
    }

    fun OnClickError(){
        response()
    }

    fun updateData() {
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