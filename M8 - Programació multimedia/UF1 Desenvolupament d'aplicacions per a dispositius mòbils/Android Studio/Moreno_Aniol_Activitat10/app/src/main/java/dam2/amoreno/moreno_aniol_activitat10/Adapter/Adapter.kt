package dam2.amoreno.moreno_aniol_activitat10.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dam2.amoreno.moreno_aniol_activitat10.Class.Valorations
import dam2.amoreno.moreno_aniol_activitat10.R

class Adapter(private val context: Context, private val valorations: MutableList<Valorations>) : BaseAdapter() {

    override fun getCount(): Int {
        return valorations.size
    }

    override fun getItem(position: Int): Valorations {
        return valorations[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_valorations, parent, false
        )

        val nomTextView: TextView = view.findViewById(R.id.textNom)
        val opinioTextView: TextView = view.findViewById(R.id.textOpinio)
        val puntuacioTextView: TextView = view.findViewById(R.id.textPuntuacio)

        val valoration = getItem(position)

        nomTextView.text = valoration.nom
        opinioTextView.text = valoration.opinio
        puntuacioTextView.text = "Puntuació: ${valoration.puntuacio}"

        return view
    }

    fun addValoration(valoration: Valorations) {
        valorations.add(valoration)
        notifyDataSetChanged()
    }
}

