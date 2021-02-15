package com.example.topfilms.data.objects

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("total_pages")
                        val totalPages: Int = 0,
                        @SerializedName("results")
                        val results: MutableList<ItemsItemViewModel> = mutableListOf())