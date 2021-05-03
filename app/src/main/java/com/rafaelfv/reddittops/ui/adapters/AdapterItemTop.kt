package com.rafaelfv.reddittops.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfv.reddittops.R
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.utils.setImageFromUrl

class AdapterItemTop(var listTop: List<Children>, private val listener: OnEventItemTop) :
    RecyclerView.Adapter<ItemTopViewHolder>() {

    interface OnEventItemTop {
        fun onBottomScroll()
        fun dismissTop(position: Int, children: Children)
        fun onTopSelected(children: Children, position: Int)
    }

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

        holder.dismissTop.setOnClickListener { listener.dismissTop(position, listTop[position]) }

        if (listTop.isNotEmpty()) {
            if (position == (listTop.size - 1)) {
                listener.onBottomScroll()
            }
        }

        holder.container.setOnClickListener {
            listener.onTopSelected(listTop[position], position)
            holder.readTopStatus.visibility = View.VISIBLE
        }

        if (listTop[position].alreadyRead) {
            holder.readTopStatus.visibility = View.VISIBLE
        } else {
            holder.readTopStatus.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = listTop.size


}