package dam2.amoreno.uf2_a6.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.uf2_a6.R
import dam2.amoreno.uf2_a6.classes.HousesList

class HousesListAdapter(private val housesList: List<HousesList>): RecyclerView.Adapter<HousesListAdapter.HousesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HousesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_houses, parent, false)
        return HousesViewHolder(view)
    }

    override fun onBindViewHolder(holder: HousesViewHolder, position: Int) {
        val currentHouse = housesList[position]
        holder.addressTextView.text = currentHouse.address
        holder.priceTextView.text = currentHouse.formattedPrice
        holder.imageView.setImageResource(currentHouse.imageResourceId)
    }

    override fun getItemCount(): Int = housesList.size

    class HousesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val addressTextView: TextView = itemView.findViewById(R.id.item_address)
        val priceTextView: TextView = itemView.findViewById(R.id.item_price)
    }
}