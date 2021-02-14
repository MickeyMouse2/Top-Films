package com.example.topfilms.util

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import com.example.topfilms.ui.adapters.RepoListAdapter
import com.example.topfilms.data.objects.UserResponse

@BindingAdapter(value = ["app:users", "app:callBacks", "app:adapter"], requireAll = true)
fun setItems(view: androidx.recyclerview.widget.RecyclerView, items: UserResponse?, callBack: RepoListAdapter.Callback?, adapter: RepoListAdapter) {
    if (items != null) {
        adapter.data = items.results
        adapter.callback = callBack!!
        adapter.notifyDataSetChanged()
    }
}

@BindingAdapter(value = ["app:loadWithUrl"])
fun ImageView.loadWithUrl(src: String) {
    com.bumptech.glide.Glide
        .with(this)
        .load(src)
        .into(this)
}