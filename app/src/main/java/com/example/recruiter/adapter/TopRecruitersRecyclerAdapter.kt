package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruiter.databinding.FinalistHomeRecyclerLayoutBinding
import com.example.recruiter.model.TopRecruiters

class TopRecruitersRecyclerAdapter (private val onClickListener: OnclickLister) : ListAdapter<TopRecruiters, TopRecruitersRecyclerAdapter.MyViewHolder>(myDiffutil) {
    object myDiffutil : DiffUtil.ItemCallback<TopRecruiters>() {
        override fun areItemsTheSame(oldItem: TopRecruiters, newItem: TopRecruiters): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TopRecruiters, newItem: TopRecruiters): Boolean {
            return oldItem.companyName == newItem.companyName
        }

    }

   inner class MyViewHolder (private var binding: FinalistHomeRecyclerLayoutBinding)
       : RecyclerView.ViewHolder(binding.root) {
           fun bind(items : TopRecruiters){
               binding.comapny1.text = items?.companyName
               binding.companyDesc.text = items?.companyDescription
           }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRecruitersRecyclerAdapter.MyViewHolder {
        return MyViewHolder(FinalistHomeRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: TopRecruitersRecyclerAdapter.MyViewHolder,
        position: Int
    ) {
        val items = getItem(position)
        holder.bind(items)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(items)
        }
    }

    class OnclickLister(val clickListener: (topRecruiters: TopRecruiters) -> Unit){
        fun onClick(topRecruiters: TopRecruiters) = clickListener(topRecruiters)
    }
}