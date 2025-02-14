package dam2.amoreno.ex2_extra.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import dam2.amoreno.ex2_extra.MainActivity
import dam2.amoreno.ex2_extra.R
import dam2.amoreno.ex2_extra.classes.ClassList

class AdapterList(private val context: Context, private val valorations: MutableList<ClassList>) : BaseAdapter() {

    override fun getCount(): Int {
        return valorations.size
    }

    override fun getItem(position: Int): ClassList {
        return valorations[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.items_list, parent, false
        )

        val textNom = view.findViewById<TextView>(R.id.textNom)
        val textCognoms = view.findViewById<TextView>(R.id.textCognoms)
        val textEdat = view.findViewById<TextView>(R.id.textEdat)
        val textPoblacio = view.findViewById<TextView>(R.id.textPoblacio)

        val valoration = getItem(position)

        textNom.text = valoration.nom
        textCognoms.text = valoration.cognoms
        textEdat.text = valoration.edat.toString()
        textPoblacio.text = valoration.poblacio

        view.setOnClickListener {
            Toast.makeText(context, valoration.nom, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
