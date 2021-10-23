package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruiter.databinding.EmployerMarketPlaceRecyclerLayoutBinding
import com.example.recruiter.model.EmpMarketPlace

class EmpMarketPlaceRecyclerAdapter :
    ListAdapter<EmpMarketPlace, EmpMarketPlaceRecyclerAdapter.MyViewHolder>(myDiffUtil) {
    inner class MyViewHolder(private var binding: EmployerMarketPlaceRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: EmpMarketPlace?) {
            binding.cat.text = items?.category
            binding.skills.text = items?.skills
        }

    }

    object myDiffUtil : DiffUtil.ItemCallback<EmpMarketPlace>() {
        override fun areItemsTheSame(oldItem: EmpMarketPlace, newItem: EmpMarketPlace): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EmpMarketPlace, newItem: EmpMarketPlace): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            EmployerMarketPlaceRecyclerLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
    }
}