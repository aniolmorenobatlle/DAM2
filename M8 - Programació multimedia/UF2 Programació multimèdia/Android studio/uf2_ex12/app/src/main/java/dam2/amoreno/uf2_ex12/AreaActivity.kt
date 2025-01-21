package dam2.amoreno.uf2_ex12

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class AreaActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area)

        val areaText = findViewById<TextView>(R.id.area)
        val dniText = findViewById<TextView>(R.id.dni)
        val edatText = findViewById<TextView>(R.id.edat)


        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        val savedArea = sharedPreferences.getString("area", "Cap area trobada")
        val savedDni = sharedPreferences.getString("dni", "Cap dni trobat")
        val savedEdat = sharedPreferences.getInt("edat", 0)

        areaText.text = "Area " + savedArea.toString()
        dniText.text = savedDni.toString()
        edatText.text = savedEdat.toString()

    }
}