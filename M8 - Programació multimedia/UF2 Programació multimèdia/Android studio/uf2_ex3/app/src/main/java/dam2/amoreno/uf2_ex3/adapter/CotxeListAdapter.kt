package dam2.amoreno.uf2_ex3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.uf2_ex3.R
import dam2.amoreno.uf2_ex3.classes.CotxeListClass

class CotxeListAdapter(private val cotxesList: List<CotxeListClass>, private val onTextViewClick: (CotxeListClass) -> Unit) : RecyclerView.Adapter<CotxeListAdapter.CotxeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CotxeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cotxe, parent, false)
        return CotxeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CotxeViewHolder, position: Int) {
        val currentCotxe = cotxesList[position]
        holder.marca.text = currentCotxe.marca
        holder.preu.text = currentCotxe.preu.toString()
        holder.any.text = currentCotxe.any.toString()
        holder.lat.text = currentCotxe.lat.toString()
        holder.long.text = currentCotxe.long.toString()

        holder.marca.setOnClickListener {
            onTextViewClick(currentCotxe)
        }
    }

    override fun getItemCount(): Int = cotxesList.size

    class CotxeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val marca: TextView = itemView.findViewById(R.id.marca)
        val preu: TextView = itemView.findViewById(R.id.preu)
        val any: TextView = itemView.findViewById(R.id.any)
        val lat: TextView = itemView.findViewById(R.id.latitud)
        val long: TextView = itemView.findViewById(R.id.longitut)
    }
}