package com.example.recruiter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recruiter.databinding.BookMarkedContactsRecyclerLayoutBinding
import com.example.recruiter.model.RegisteredFinalist
import com.example.recruiter.ui.EmployerHomeFragmentDirections

class RegisteredFinalistAdapter : ListAdapter<RegisteredFinalist, RegisteredFinalistAdapter.RegisteredViewHolder>(RegisteredDiffUtil){

    /*inner class registeredViewHolder(private var binding: BookmarkedContactsRecyclerAdapter)
        :RecyclerView.ViewHolder(binding.root) {

    }*/

    inner class RegisteredViewHolder(private var binding: BookMarkedContactsRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: RegisteredFinalist?) {
            binding.name.text = items?.name
            binding.speciality.text = items?.speciality
            Glide.with(binding.circleImageView)
                .load(items?.imageUrl)
                .into(binding.circleImageView)
        }

    }

    object RegisteredDiffUtil : DiffUtil.ItemCallback<RegisteredFinalist>() {
        override fun areItemsTheSame(
            oldItem: RegisteredFinalist,
            newItem: RegisteredFinalist
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RegisteredFinalist,
            newItem: RegisteredFinalist
        ): Boolean {
            return oldItem.userID == newItem.userID
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisteredViewHolder {
        return  RegisteredViewHolder(BookMarkedContactsRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RegisteredViewHolder, position: Int) {
        val profiles = getItem(position)
        holder.bind(profiles)
        holder.itemView.setOnClickListener {
            val action = EmployerHomeFragmentDirections.actionEmployerHomeFragmentToChatRoomFragment3(profiles)
            Navigation.findNavController(it).navigate(action)
        }
        /*holder.itemView.setOnClickListener {
            val action = EmployerHomeFragmentDirections.actionEmployerHomeFragmentToChatRoomFragment3(profiles)
            Navigation.findNavController(it).navigate(action)
        }*/
    }
}