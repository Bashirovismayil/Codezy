package com.bashir.codezy.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bashir.codezy.R


class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    val dateTextView: TextView = itemView.findViewById(R.id.date_text_view)
    val contentTextView: TextView = itemView.findViewById(R.id.content_text_view)




}
