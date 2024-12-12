package dam2.amoreno.uf2_a6.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.uf2_a6.R

/*
   Els RecyclerViews són més eficients que els ListViews, ja que reutilitzen els elements en pantalla.
   Això fa que, quan un element desapareix de la pantalla, s'aprofiti la seva estructura per tal de
   mostrar-ne un de nou, millorant l'eficiència i reduint el nombre de recursos utilitzats.
 */
class SliderAdapter(private val context: Context, private var imageList: List<Int>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    /*
       ViewHolder per a cadascuna de les imatges. Recordeu que un ViewHolder és un contenidor
       que manté referències a les vistes.
     */
    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    // Retorna la quantitat d'imatges
    override fun getItemCount(): Int = imageList.size

    // Crea el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        // Hem de cridar l'element slider de l'XML
        val view = LayoutInflater.from(context).inflate(R.layout.slider, parent, false)
        return SliderViewHolder(view)
    }

    // Vincula el llistat d'imatges al view holder
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
    }
}