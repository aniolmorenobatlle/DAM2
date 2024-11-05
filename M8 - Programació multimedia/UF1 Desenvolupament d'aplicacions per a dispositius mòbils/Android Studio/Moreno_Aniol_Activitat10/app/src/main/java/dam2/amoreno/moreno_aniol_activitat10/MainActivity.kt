package dam2.amoreno.moreno_aniol_activitat10

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<Button>(R.id.imatge)
        val description = findViewById<Button>(R.id.descripcio)
        val valorations = findViewById<Button>(R.id.valoracions)

        val fragmentImage = findViewById<FragmentContainerView>(R.id.fragmentImage)
        val fragmentDescription = findViewById<FragmentContainerView>(R.id.fragmentDescription)
        val fragmentValorations = findViewById<FragmentContainerView>(R.id.fragmentValorations)

        image.setOnClickListener {
            fragmentImage.visibility = View.VISIBLE
            fragmentDescription.visibility = View.GONE
            fragmentValorations.visibility = View.GONE
        }

        description.setOnClickListener {
            fragmentImage.visibility = View.GONE
            fragmentDescription.visibility = View.VISIBLE
            fragmentValorations.visibility = View.GONE
        }

        valorations.setOnClickListener {
            fragmentImage.visibility = View.GONE
            fragmentDescription.visibility = View.GONE
            fragmentValorations.visibility = View.VISIBLE
        }

    }
}