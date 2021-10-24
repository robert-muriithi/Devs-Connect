package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruiter.databinding.FinalistMarketPlaceRecyclerLayoutBinding
import com.example.recruiter.model.FinalistMarketPlace

class FinMarketPlaceRecyclerAdapter (private val onClickListener : OnClickListener) : ListAdapter<FinalistMarketPlace, FinMarketPlaceRecyclerAdapter.MyViewHolder>(MyDiffUtil) {
   inner class MyViewHolder(private var binding: FinalistMarketPlaceRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(items: FinalistMarketPlace?) {
           binding.jTitle.text = items?.jobTitle
           binding.jRole.text = items?.jobRole
           binding.jDescription.text = items?.jobDesc
       }

   }
    object MyDiffUtil : DiffUtil.ItemCallback<FinalistMarketPlace>() {
        override fun areItemsTheSame(
            oldItem: FinalistMarketPlace,
            newItem: FinalistMarketPlace
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FinalistMarketPlace,
            newItem: FinalistMarketPlace
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup,  viewType: Int ): MyViewHolder {
        return MyViewHolder(FinalistMarketPlaceRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(items)
        }
    }
    class OnClickListener(val clickListener : (finMrkt: FinalistMarketPlace) -> Unit){
        fun onClick(finMrkt: FinalistMarketPlace) = clickListener(finMrkt)
    }
}