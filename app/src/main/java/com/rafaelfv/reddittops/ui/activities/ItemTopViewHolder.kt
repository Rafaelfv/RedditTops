package com.rafaelfv.reddittops.ui.activities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfv.reddittops.R

class ItemTopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageTop = itemView.findViewById<ImageView>(R.id.image_view_item_top)
    val titleTop = itemView.findViewById<TextView>(R.id.title_item_top)
    val timeTop = itemView.findViewById<TextView>(R.id.time_item_top)
    val authorTop = itemView.findViewById<TextView>(R.id.author_item_type)
    val contentTop = itemView.findViewById<TextView>(R.id.content_item_top)
    val commentsTop = itemView.findViewById<TextView>(R.id.comments_item_top)
    val dismissTop = itemView.findViewById<ImageView>(R.id.btn_dismiss_item_top)
    val readTopStatus = itemView.findViewById<ImageView>(R.id.read_item_top)
    val container = itemView.findViewById<ConstraintLayout>(R.id.container_item_top)

}