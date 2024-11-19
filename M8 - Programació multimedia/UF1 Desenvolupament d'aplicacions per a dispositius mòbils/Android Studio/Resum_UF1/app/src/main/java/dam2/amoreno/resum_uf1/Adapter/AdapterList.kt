package dam2.amoreno.resum_uf1.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import dam2.amoreno.resum_uf1.Classes.Valorations
import dam2.amoreno.resum_uf1.R

class AdapterList(private val context: Context, private val valorations: MutableList<Valorations>) : BaseAdapter() {

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
            R.layout.items_lists, parent, false
        )

        val textName = view.findViewById<TextView>(R.id.textName)
        val textOpinion = view.findViewById<TextView>(R.id.textOpinion)
        val textValoration = view.findViewById<TextView>(R.id.textValoration)

        val valoration = getItem(position)

        textName.text = valoration.nom
        textOpinion.text = valoration.opinio
        textValoration.text = "Puntuació: ${valoration.puntuacio}"

        view.setOnClickListener {
            Toast.makeText(context, valoration.nom, Toast.LENGTH_SHORT).show()
        }

        return view
    }

    fun addValoration(valoration: Valorations) {
        valorations.add(valoration)
        notifyDataSetChanged()
    }
}
