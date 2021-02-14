package com.example.topfilms.util

import java.io.IOException
import com.example.topfilms.data.objects.main.Result

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))
    }
}