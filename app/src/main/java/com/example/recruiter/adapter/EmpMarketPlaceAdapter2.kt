package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recruiter.databinding.Marketplace2layoutBinding
import com.example.recruiter.model.BookmarkedContacts
import com.example.recruiter.model.EmpMarketPlace2

class EmpMarketPlaceAdapter2 (private val onClickListener: OnClickListener) :
    ListAdapter<BookmarkedContacts, EmpMarketPlaceAdapter2.myViewHolder>(myDiffUtil) {
    object myDiffUtil : DiffUtil.ItemCallback<BookmarkedContacts>() {
        override fun areItemsTheSame(oldItem: BookmarkedContacts, newItem: BookmarkedContacts): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BookmarkedContacts,
            newItem: BookmarkedContacts
        ): Boolean {
            return oldItem.name == oldItem.name
        }

    }

    inner class myViewHolder(private var binding: Marketplace2layoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: BookmarkedContacts) {
            Glide.with(binding.markeplace2Image)
                .load(items?.image)
                .into(binding.markeplace2Image)
            binding.marketplace2Name.text = items?.name
            binding.marketplace2Category.text = items?.speciality



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(
            Marketplace2layoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(items)
        }
    }
    class OnClickListener(val clickListener : (empl: BookmarkedContacts) -> Unit)  {
        fun onClick(emp: BookmarkedContacts) = clickListener(emp)
    }
}