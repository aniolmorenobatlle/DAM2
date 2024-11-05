package dam2.amoreno.moreno_aniol_activitat9.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import dam2.amoreno.moreno_aniol_activitat9.R
import dam2.amoreno.moreno_aniol_activitat9.Classes.Students

class Adapter(
    private val mContext: Context,
    private var llistaStudents: MutableList<Students>
) : ArrayAdapter<Students>(mContext, 0, llistaStudents), Filterable {

    private var llistaOriginal: MutableList<Students> = llistaStudents.toMutableList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout: View
        val holder: ViewHolder

        if (convertView == null) {
            layout = LayoutInflater.from(mContext).inflate(R.layout.items, parent, false)
            holder = ViewHolder()
            holder.nom = layout.findViewById(R.id.nom)
            holder.poblacio = layout.findViewById(R.id.poblacio)
            holder.foto = layout.findViewById(R.id.foto)
            holder.hora = layout.findViewById(R.id.hora)
            layout.tag = holder
        } else {
            layout = convertView
            holder = layout.tag as ViewHolder
        }

        val student = llistaStudents[position]
        holder.nom?.text = student.nom
        holder.poblacio?.text = student.poblacio
        holder.foto?.setImageResource(student.foto)
        holder.hora?.text = student.hora

        layout.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder
                .setTitle("Has clicat al xat de ${student.nom}")
                .setMessage("Aquest es el xat de ${student.nom}")
                .setNegativeButton("Tancar") { dialog, which ->
                    dialog.dismiss()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return layout
    }

    // Filtre per buscar
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()

                if (constraint.isNullOrEmpty()) {
                    filterResults.values = llistaOriginal
                    filterResults.count = llistaOriginal.size
                } else {
                    val query = constraint.toString().lowercase().trim() // Ignorar les majuscules i espais
                    val filteredList = llistaOriginal.filter {
                        it.nom.lowercase().contains(query)
                    }
                    filterResults.values = filteredList
                    filterResults.count = filteredList.size
                }

                return filterResults
            }

            // Actualitzar resultats de la llista
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                llistaStudents.clear()
                llistaStudents.addAll(results?.values as MutableList<Students>)
                notifyDataSetChanged()
            }
        }
    }

    private class ViewHolder {
        var nom: TextView? = null
        var poblacio: TextView? = null
        var foto: ImageView? = null
        var hora: TextView? = null
    }
}