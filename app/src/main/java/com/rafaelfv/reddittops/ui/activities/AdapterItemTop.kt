package com.rafaelfv.reddittops.ui.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaelfv.reddittops.R
import com.rafaelfv.reddittops.repository.model.Children

class AdapterItemTop(var listTop: List<Children>) : RecyclerView.Adapter<ItemTopViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTopViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_top, parent, false)
        context = parent.context
        return ItemTopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemTopViewHolder, position: Int) {
        holder.titleTop.text = listTop[position].data.name
        holder.authorTop.text = listTop[position].data.author
        holder.commentsTop.text = "${listTop[position].data.num_comments} comments"
        holder.imageTop.setImageFromUrl(listTop[position].data.thumbnail)
        holder.contentTop.text = listTop[position].data.title


    }

    override fun getItemCount(): Int = listTop.size

    private fun ImageView.setImageFromUrl(url: String) =
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.empty_image)
            .into(this)

}