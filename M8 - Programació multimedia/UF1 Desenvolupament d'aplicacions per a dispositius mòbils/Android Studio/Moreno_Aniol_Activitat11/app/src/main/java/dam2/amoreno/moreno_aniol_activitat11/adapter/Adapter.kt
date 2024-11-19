package dam2.amoreno.moreno_aniol_activitat11.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dam2.amoreno.moreno_aniol_activitat11.FragmentsActivity
import dam2.amoreno.moreno_aniol_activitat11.R
import dam2.amoreno.moreno_aniol_activitat11.classes.Products

class Adapter(context: Context, private val productList: List<Products>) :
    ArrayAdapter<Products>(context, 0, productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.items_products, parent, false)

        val product = getItem(position)

        val textViewMarca = view.findViewById<TextView>(R.id.textViewMarca)
        val textViewModel = view.findViewById<TextView>(R.id.textViewModel)
        val textViewUnitat = view.findViewById<TextView>(R.id.textViewUnitat)

        textViewMarca.text = product?.marca
        textViewModel.text = product?.model
        textViewUnitat.text = product?.quantitat

        // Afegir listener al clic del item
        view.setOnClickListener {
            val intent = Intent(context, FragmentsActivity::class.java)
            intent.putExtra("marca", product?.marca)
            intent.putExtra("model", product?.model)
            intent.putExtra("quantitat", product?.quantitat)
            intent.putExtra("nomImatge", product?.nomImatge)
            context.startActivity(intent)
        }

        return view
    }
}

