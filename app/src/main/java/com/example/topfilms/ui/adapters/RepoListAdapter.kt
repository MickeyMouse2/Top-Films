package com.example.topfilms.ui.adapters

import android.view.*
import kotlinx.android.synthetic.main.item_repo.view.*
import com.example.topfilms.R
import com.example.topfilms.data.objects.ItemsItem
import com.example.topfilms.util.loadWithUrl

class RepoListAdapter(
        var data: List<ItemsItem> = ArrayList(),
        var callback: Callback
) : androidx.recyclerview.widget.RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

  interface Callback {
    fun onShowLastItem()
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindForecast(data[position])
    if (position + 1 >= data.size) {
      if(callback is Callback) {
        (callback as Callback).onShowLastItem()
      }
    }
  }

  override fun getItemCount() = data.size



  class ViewHolder(view: View) :
          androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    fun bindForecast(item: ItemsItem) {
      with(itemView){
        if(!item.backdrop_path.isNullOrBlank())
          userAvatar.loadWithUrl(item.backdrop_path)
        if(!item.backdrop_path.isNullOrBlank())
          userName.text = item.title
      }
    }

  }



}
