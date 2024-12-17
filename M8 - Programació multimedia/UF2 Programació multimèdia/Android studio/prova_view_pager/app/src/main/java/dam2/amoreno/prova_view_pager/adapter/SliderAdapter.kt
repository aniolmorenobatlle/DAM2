package dam2.amoreno.prova_view_pager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.prova_view_pager.R


class SliderAdapter(private val context: Context, private var imageList: List<Int>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {


    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.slider, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
    }
}