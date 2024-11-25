package dam2.amoreno.provaexamenextra

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val marcaResult = findViewById<TextView>(R.id.marcaResult)
        val modelResult = findViewById<TextView>(R.id.modelResult)
        val matriculaResult = findViewById<TextView>(R.id.matriculaResult)
        val anyResult = findViewById<TextView>(R.id.anyResult)

        val sharedPref: SharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)

        val savedMarca = sharedPref.getString("marca", "Default")
        val savedModel = sharedPref.getString("model", "Default")
        val savedMatricula = sharedPref.getString("matricula", "Default")
        val savedAny = sharedPref.getString("any", "Default")

        marcaResult.text = savedMarca
        modelResult.text = savedModel
        matriculaResult.text = savedMatricula
        anyResult.text = savedAny
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.seguent -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}