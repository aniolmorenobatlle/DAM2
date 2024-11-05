package dam2.amoreno.moreno_aniol_activitat10.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import dam2.amoreno.moreno_aniol_activitat10.Adapter.Adapter
import dam2.amoreno.moreno_aniol_activitat10.Class.Valorations
import dam2.amoreno.moreno_aniol_activitat10.R

class FragmentValorations : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: Adapter
    private val valorations = mutableListOf<Valorations>()  // Definir llista com a mutable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_valorations, container, false)

        listView = view.findViewById(R.id.listValorations)

        // Omple la llista inicial amb les valoracions
        valorations.add(Valorations("Joan", "Bon producte", 10))
        valorations.add(Valorations("Maria", "M'esperava més però està bé", 6))
        valorations.add(Valorations("Pau", "No es el que esperava", 2))

        adapter = Adapter(requireContext(), valorations)  // Passa llista mutable
        listView.adapter = adapter

        val add = view.findViewById<Button>(R.id.buttonAdd)
        add.setOnClickListener {
            addValoration()
        }

        return view
    }

    private fun addValoration() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_valorations, null)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
            .setTitle("Afegeix una valoració")
            .setPositiveButton("Guardar") { dialog, which ->

                val name = dialogView.findViewById<EditText>(R.id.editTextName).text.toString()
                val opinion = dialogView.findViewById<EditText>(R.id.editTextOpinion).text.toString()
                val ratingString = dialogView.findViewById<EditText>(R.id.editTextRating).text.toString()
                val rating = ratingString.toIntOrNull() ?: 0

                // Afegir la nova valoracio a la llista
                if (rating in 1..10) {
                    val newValoration = Valorations(name, opinion, rating)
                    adapter.addValoration(newValoration)
                } else {
                    Toast.makeText(requireContext(), "La puntuació ha de ser entre 1 i 10", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Tancar") { dialog, which ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

