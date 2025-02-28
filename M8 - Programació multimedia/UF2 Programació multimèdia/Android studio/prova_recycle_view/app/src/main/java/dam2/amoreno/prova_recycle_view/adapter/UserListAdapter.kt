package dam2.amoreno.prova_recycle_view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.prova_recycle_view.R
import dam2.amoreno.prova_recycle_view.classes.UserListClass

class UserListAdapter(private val userList: List<UserListClass>) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.nameTextView.text = currentUser.name
        holder.imageView.setImageResource(currentUser.imageResourceId)
    }


    override fun getItemCount(): Int = userList.size


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val nameTextView: TextView = itemView.findViewById(R.id.item_name)
    }
}
