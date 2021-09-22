package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recruiter.databinding.BookMarkedContactsRecyclerLayoutBinding
import com.example.recruiter.model.BookmarkedContacts

class BookmarkedContactsRecyclerAdapter : ListAdapter<BookmarkedContacts,BookmarkedContactsRecyclerAdapter.MyViewHolder>(myDiffUtil) {
    object myDiffUtil: DiffUtil.ItemCallback<BookmarkedContacts>() {
        override fun areItemsTheSame(
            oldItem: BookmarkedContacts,
            newItem: BookmarkedContacts
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BookmarkedContacts,
            newItem: BookmarkedContacts
        ): Boolean {
            return newItem.id == oldItem.id
        }

    }
    inner class MyViewHolder(private var binding: BookMarkedContactsRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: BookmarkedContacts?) {
            binding.name.text = items?.name
            binding.speciality.text = items?.speciality
            Glide.with(binding.circleImageView)
                .load(items?.image)
                .into(binding.circleImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(BookMarkedContactsRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
    }
}