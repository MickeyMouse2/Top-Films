package com.example.topfilms.data.objects

import com.google.gson.annotations.SerializedName

data class ItemsItemViewModel(@SerializedName("title")
                     val title: String,
                              @SerializedName("backdrop_path")
                     var backdrop_path: String)