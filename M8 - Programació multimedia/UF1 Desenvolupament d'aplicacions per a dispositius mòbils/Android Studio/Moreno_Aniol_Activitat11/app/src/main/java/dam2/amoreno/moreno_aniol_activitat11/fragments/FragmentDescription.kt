package dam2.amoreno.moreno_aniol_activitat11.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dam2.amoreno.moreno_aniol_activitat11.R
import org.w3c.dom.Text

class FragmentDescription : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description, container, false)

        val marca = arguments?.getString("marca")
        val model = arguments?.getString("model")
        val quantitat = arguments?.getString("quantitat")


        val textMarca = view.findViewById<TextView>(R.id.textViewMarcaResult)
        val textModel = view.findViewById<TextView>(R.id.textViewModelResult)
        val textQuantitat = view.findViewById<TextView>(R.id.textViewQuantitatResult)

        textMarca.text = marca
        textModel.text = model
        textQuantitat.text = quantitat

        return view
    }
}
