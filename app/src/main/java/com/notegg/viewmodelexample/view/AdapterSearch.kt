package com.notegg.viewmodelexample.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.notegg.viewmodelexample.DaumSearchDocument
import com.notegg.viewmodelexample.SearchItem
import com.notegg.viewmodelexample.databinding.ItemRecyclerSearchBinding

class AdapterSearch : RecyclerView.Adapter<AdapterSearch.Holder>() {

    val list = ArrayList<DaumSearchDocument>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerSearchBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setContent(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun resetList(list: List<DaumSearchDocument>) {
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemRecyclerSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        }

        fun setContent(item: DaumSearchDocument) {
            val item = item.getSearchItem()
            binding.item = item
            Glide.with(binding.root).load(item.thumbnail).into(binding.image)
        }

    }

}