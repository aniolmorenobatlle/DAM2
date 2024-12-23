package edu.uoc.android.restservice.rest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.uoc.android.restservice.R
import edu.uoc.android.restservice.rest.classes.UserListClass

class UserListAdapter(private val userList: MutableList<UserListClass>) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.usernameTextView.text = user.username
        Glide.with(holder.itemView.context)
            .load(user.userImage)
            .into(holder.avatarImageView)
    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
    }
}
