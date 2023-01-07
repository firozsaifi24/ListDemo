package com.firoz.listdemo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.firoz.listdemo.R
import com.firoz.listdemo.databinding.ActivityMainBinding
import com.firoz.listdemo.view.adapter.CommonLoadStateAdapter
import com.firoz.listdemo.view.adapter.UserListAdapter
import com.firoz.listdemo.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        submitUserList()

    }

    private fun submitUserList() {
        val userListAdapter = UserListAdapter(this)
        binding.recyclerView.adapter = userListAdapter.withLoadStateHeaderAndFooter(
            header = CommonLoadStateAdapter {userListAdapter.retry()},
            footer = CommonLoadStateAdapter {userListAdapter.retry()}
        )
        mainViewModel.getUsersFlow().observe(this@MainActivity) {
            launchOnLifecycleScope {
                //val user : UserListItem = it.filter { user -> user.id == "20" }
                //Log.v("users == ",user.toString())
                userListAdapter.submitData(it)
                userListAdapter.notifyDataSetChanged()


            }
        }
    }

    private fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        lifecycleScope.launch {
            execute()
        }
    }

}