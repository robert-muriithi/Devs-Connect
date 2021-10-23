package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recruiter.databinding.Marketplace2layoutBinding
import com.example.recruiter.model.EmpMarketPlace2

class EmpMarketPlaceAdapter2 :
    ListAdapter<EmpMarketPlace2, EmpMarketPlaceAdapter2.myViewHolder>(myDiffUtil) {
    object myDiffUtil : DiffUtil.ItemCallback<EmpMarketPlace2>() {
        override fun areItemsTheSame(oldItem: EmpMarketPlace2, newItem: EmpMarketPlace2): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: EmpMarketPlace2,
            newItem: EmpMarketPlace2
        ): Boolean {
            return oldItem.name == oldItem.name
        }

    }

    inner class myViewHolder(private var binding: Marketplace2layoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: EmpMarketPlace2) {
            Glide.with(binding.markeplace2Image)
                .load(items?.imageURL)
                .into(binding.markeplace2Image)
            binding.marketplace2Name.text = items?.name
            binding.marketplace2About.text = items?.bio
            binding.marketplace2Category.text = items?.category


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
        val item = getItem(position)
        holder.bind(item)
    }
}