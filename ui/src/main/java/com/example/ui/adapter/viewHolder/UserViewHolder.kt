package com.example.ui.adapter.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.ItemUserBinding
import com.example.ui.model.UserModel
import com.example.ui.utils.loadUrl

class UserViewHolder(private val binding: ItemUserBinding,
                     private val userCallback: (UserModel) -> Unit) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: UserModel) {
        val name = "${item.title.capitalize()}, ${item.lastName} ${item.firstName} "
        binding.userDetails.text = name
        binding.userImage.loadUrl(item.picture)
        binding.userView.setOnClickListener {
            userCallback.invoke(item)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            userCallback: (UserModel) -> Unit
        ): UserViewHolder {
            val binding =
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserViewHolder(binding, userCallback)
        }

    }
}