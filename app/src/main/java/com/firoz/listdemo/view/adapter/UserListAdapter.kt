package com.firoz.listdemo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.firoz.listdemo.databinding.ItemUsersBinding
import com.firoz.listdemo.model.UserDataResponse
import com.firoz.listdemo.utils.Utils

internal class UserListAdapter(
    private val mContext: Context,
) : PagingDataAdapter<UserDataResponse, UserListAdapter.MyViewHolder>(UserComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val user: UserDataResponse? = getItem(position)
        viewHolder.bind(user)
    }

    inner class MyViewHolder(val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserDataResponse?) {
            user?.let {
                binding.tvName.text = user.author
                binding.layoutMain.setOnClickListener {
                    Utils.showToast(mContext, user.author!!)
                }
            }
        }
    }

    object UserComparator : DiffUtil.ItemCallback<UserDataResponse>() {
        override fun areItemsTheSame(oldItem: UserDataResponse, newItem: UserDataResponse): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: UserDataResponse, newItem: UserDataResponse): Boolean {
            return oldItem == newItem
        }
    }
}