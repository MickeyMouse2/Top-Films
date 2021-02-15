package com.example.topfilms.util

import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.topfilms.ui.adapters.ItemsAdapter
import com.example.topfilms.data.objects.UserResponse

@BindingAdapter(value = ["app:users", "app:callBacks", "app:adapter"], requireAll = true)
fun setItems(view: androidx.recyclerview.widget.RecyclerView, items: UserResponse?, callBack: ItemsAdapter.Callback?, adapter: ItemsAdapter) {
    if (items != null) {
        adapter.data = items.results
        adapter.callback = callBack!!
        adapter.notifyDataSetChanged()
    }
}

@BindingAdapter("app:imageUri")
fun loadImageWithUri(view : View, imageUri: String){
    Glide.with(view.context).load(Uri.parse(imageUri)).into(view as ImageView)
}

@BindingAdapter("customVisibility")
fun setVisibility(view : View, visible : Boolean) {
    view.visibility = if (visible) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["app:loadWithUrl"])
fun ImageView.loadWithUrl(src: String) {
    com.bumptech.glide.Glide
            .with(this)
            .load(src)
            .into(this)
}