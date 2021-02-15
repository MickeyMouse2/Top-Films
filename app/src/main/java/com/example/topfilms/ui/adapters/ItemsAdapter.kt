package com.example.topfilms.ui.adapters

import android.view.*
import kotlinx.android.synthetic.main.item_repo.view.*
import com.example.topfilms.R
import com.example.topfilms.data.objects.ItemsItemViewModel
import com.example.topfilms.databinding.ItemRepoBinding
import com.example.topfilms.util.loadWithUrl

class ItemsAdapter(
    var data: List<ItemsItemViewModel> = ArrayList(),
    var callback: Callback
) : androidx.recyclerview.widget.RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    interface Callback {
        fun onShowLastItem()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemRepoBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(data[position])
        if (position + 1 >= data.size) {
            if (callback is Callback) {
                (callback as Callback).onShowLastItem()
            }
        }
    }

    override fun getItemCount() = data.size


    inner class ViewHolder(val binding: ItemRepoBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bindForecast(itemViewModel: ItemsItemViewModel) {
            binding.item = itemViewModel
            binding.executePendingBindings()
        }

    }


}
