package com.example.topfilms.data

import com.example.topfilms.api.PopularFilms
import com.example.topfilms.data.objects.UserResponse
import com.example.topfilms.util.safeApiCall
import com.example.topfilms.data.objects.main.Result
import java.io.IOException

class NetworkManager(private val gitResponse: PopularFilms) {

    suspend fun searchUsers(page: Int) = safeApiCall(
            call = {
                searchUserByFollowers(page)
            },
            errorMessage = "Error get user data"
    )

    private suspend fun searchUserByFollowers(page: Int):  Result<UserResponse> {
        val response = gitResponse.searchUser(page = page).await()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(
                IOException("Error getting github data ${response.code()} ${response.message()}")
        )
    }
}


//    fun getUserByNickName(nick: String, onUpdate: (User) -> Unit, onError: (Any) -> Unit){
//        val resultObservable =
//            gitResponse.getUserByUsername(nick)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe ({
//                        result ->
//                    Log.e(this.javaClass.name, result.toString())
//                    onUpdate(result)
//                }, { error ->
//                    onError(error)
//                })
//    }