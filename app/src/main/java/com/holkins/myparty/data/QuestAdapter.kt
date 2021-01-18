package com.holkins.myparty.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.holkins.myparty.R
import com.holkins.myparty.model.Person
import com.squareup.picasso.Picasso

class QuestAdapter (var items: List<Person>, val callback: Callback) : RecyclerView.Adapter<QuestAdapter.QuestHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = QuestHolder(LayoutInflater.from(parent.context).inflate(R.layout.quest_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: QuestHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class QuestHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val guestTextView = itemView.findViewById<TextView>(R.id.guestTextView)
        private val guestImageView = itemView.findViewById<ImageView>(R.id.guestImageView)

        fun bind(item: Person) {

            val url = item.pictureURL
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.placeholder)
                .into(guestImageView)

            guestTextView.text = item.name

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }

        }
    }

    interface Callback {
        fun onItemClicked(item: Person)
    }
}