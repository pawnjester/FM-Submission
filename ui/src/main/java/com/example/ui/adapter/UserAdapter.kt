package com.example.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapter.viewHolder.UserViewHolder
import com.example.ui.model.UserModel
import javax.inject.Inject

class UserAdapter @Inject constructor(

) : RecyclerView.Adapter<UserViewHolder>() {
    var userCallback: ((UserModel) -> Unit)? = null

    private val listOfUsers = mutableListOf<UserModel>()

    fun setUserData(items: List<UserModel>) {
        listOfUsers.clear()
        listOfUsers.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent, userCallback ?: {})
    }

    override fun getItemCount(): Int = listOfUsers.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listOfUsers[position])
    }


}