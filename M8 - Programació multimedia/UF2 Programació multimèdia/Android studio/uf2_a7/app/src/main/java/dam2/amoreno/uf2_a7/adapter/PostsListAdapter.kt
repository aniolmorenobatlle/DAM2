package dam2.amoreno.uf2_a7.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.uf2_a7.R
import dam2.amoreno.uf2_a7.classes.PostsListClass

class PostsListAdapter(private val userList: List<PostsListClass>) : RecyclerView.Adapter<PostsListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_posts, parent, false)
        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.avatar.setImageResource(currentUser.avatar)
        holder.username.text = currentUser.username
        holder.photo.setImageResource(currentUser.photo)
        holder.comment.text = currentUser.comment
        holder.likes.text = currentUser.likes.toString()
        holder.comments.text = currentUser.comments.toString()
        holder.shareds.text = currentUser.shareds.toString()
        holder.date.text = currentUser.date
    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val username: TextView = itemView.findViewById(R.id.username)
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val comment: TextView = itemView.findViewById(R.id.comment)
        val likes: TextView = itemView.findViewById(R.id.likes)
        val comments: TextView = itemView.findViewById(R.id.comments)
        val shareds: TextView = itemView.findViewById(R.id.shareds)
        val date: TextView = itemView.findViewById(R.id.date)
    }
}