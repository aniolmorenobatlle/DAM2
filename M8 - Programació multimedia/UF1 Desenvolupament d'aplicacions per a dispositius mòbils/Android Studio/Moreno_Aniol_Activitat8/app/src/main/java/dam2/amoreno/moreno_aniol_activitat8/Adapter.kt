package dam2.amoreno.moreno_aniol_activitat8

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Adapter(private val mContext: Context, private val llistaStudents: MutableList<Students>) : ArrayAdapter<Students>(mContext, 0, llistaStudents) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout: View
        val holder: ViewHolder

        if (convertView == null) {
            layout = LayoutInflater.from(mContext).inflate(R.layout.items, parent, false)
            holder = ViewHolder()
            holder.nom = layout.findViewById(R.id.nom)
            holder.poblacio = layout.findViewById(R.id.poblacio)
            holder.foto = layout.findViewById(R.id.foto)
            holder.buttonFitxa = layout.findViewById(R.id.buttonFitxa)
            layout.tag = holder
        } else {
            layout = convertView
            holder = layout.tag as ViewHolder
        }

        val student = llistaStudents[position]
        holder.nom?.text = student.nom
        holder.poblacio?.text = student.poblacio
        holder.foto?.setImageResource(student.foto)

        holder.buttonFitxa?.setOnClickListener {
            val intent = Intent(mContext, SecondActivity::class.java)
            intent.putExtra("students", student)
            mContext.startActivity(intent)
        }

        return layout
    }

    private class ViewHolder {
        var nom: TextView? = null
        var poblacio: TextView? = null
        var foto: ImageView? = null
        var buttonFitxa: Button? = null
    }

    // Afegir un nou alumne a la llista
    fun addStudent(newStudent: Students) {
        llistaStudents.add(newStudent) // Canviar la llistaStudents a MutableList perque sino no es comptabile amb .add
        notifyDataSetChanged()  // Notifica a l'adapter perquè actualitzi la vista
    }
}
