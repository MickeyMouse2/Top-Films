package com.example.topfilms.data.objects

import com.google.gson.annotations.SerializedName

data class ItemsItem(@SerializedName("title")
                     val title: String = "",
                     @SerializedName("backdrop_path")
                     val backdrop_path: String = "")