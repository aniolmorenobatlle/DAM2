package dam2.amoreno.moreno_aniol_activitat10.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import dam2.amoreno.moreno_aniol_activitat10.R

class FragmentDescription : Fragment() {
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description, container, false)

        sharedPref = requireActivity().getSharedPreferences("sharedPref", MODE_PRIVATE)

        val description = view.findViewById<TextView>(R.id.textDescription)
        val editDescription = view.findViewById<Button>(R.id.buttonEdit)

        val editor = sharedPref.edit()
        if (sharedPref.getString("description", "").isNullOrEmpty()) {
            editor.putString(
                "description",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            )
            editor.apply()
        }

        description.text = sharedPref.getString("description", "")

        editDescription.setOnClickListener {
            editDescription(description)
        }

        return view
    }

    private fun editDescription(descriptionTextView: TextView) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_description, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextDescription)


        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
            .setTitle("Edita la descripció")
            .setPositiveButton("Guardar") { dialog, which ->
                val newDescription = editText.text.toString()
                sharedPref.edit().putString("description", newDescription).apply()
                descriptionTextView.text = newDescription
            }
            .setNegativeButton("Cancel·lar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
