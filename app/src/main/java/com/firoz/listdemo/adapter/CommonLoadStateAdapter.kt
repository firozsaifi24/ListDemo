package com.firoz.listdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firoz.listdemo.databinding.LoadStateViewBinding

class CommonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CommonLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) { holder.bind(loadState) }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =  LoadStateViewHolder(
        LoadStateViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )

    class LoadStateViewHolder(
        val binding: LoadStateViewBinding,
        private val retry: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage
            }
            binding.progressbar.visibility = if(loadState is LoadState.Loading) View.VISIBLE else View.GONE
            binding.tvRetry.visibility = if(loadState is LoadState.Error) View.VISIBLE else View.GONE
            binding.textViewError.visibility  = if(loadState is LoadState.Error) View.VISIBLE else View.GONE

            binding.tvRetry.setOnClickListener { retry() }
        }
    }
}