package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruiter.databinding.FinalistHomeRecyclerLayoutBinding
import com.example.recruiter.model.CompanyInfor

class FinalistHomeAdapter( private val onclicklistener: OnClickListener) :
    ListAdapter<CompanyInfor, FinalistHomeAdapter.MyViewHolder>(HomeDiffutil) {
    object HomeDiffutil : DiffUtil.ItemCallback<CompanyInfor>() {
        override fun areItemsTheSame(oldItem: CompanyInfor, newItem: CompanyInfor): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CompanyInfor, newItem: CompanyInfor): Boolean {
            return oldItem.compName == newItem.compName
        }

    }

    inner class MyViewHolder(private var binding: FinalistHomeRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: CompanyInfor) {
            binding.comapny1.text = items.compName
            binding.companyDesc.text = items.compAbout

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FinalistHomeRecyclerLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onclicklistener.onClick(item)
        }
    }

    class OnClickListener(val clickListener : (companyInfo: CompanyInfor) -> Unit)  {
        fun onClick(companyInfo: CompanyInfor) = clickListener(companyInfo)
    }
}