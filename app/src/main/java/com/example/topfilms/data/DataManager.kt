package com.example.topfilms.data

import com.example.topfilms.data.objects.UserResponse
import com.example.topfilms.util.exhaustive
import kotlinx.coroutines.*
import com.example.topfilms.data.objects.main.Result

class DataManager(private val networkManager: NetworkManager) {

    private val parentJob = SupervisorJob()
    private val bgScope = CoroutineScope(Dispatchers.IO + parentJob)

    fun getUsersAsync(onSuccess: (UserResponse) -> Unit, onError: (String) -> Unit, page: Int) = bgScope.launch {
        val result = networkManager.searchUsers(page)
        when (result) {
            is Result.Success -> onSuccess(result.data)
            is Result.Error -> onError(result.exception.localizedMessage)
        }.exhaustive
    }
}