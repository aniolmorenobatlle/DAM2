package dam2.amoreno.provaexamenextra

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marca = findViewById<EditText>(R.id.marca)
        val model = findViewById<EditText>(R.id.model)
        val matricula = findViewById<EditText>(R.id.matricula)
        val any = findViewById<EditText>(R.id.any)
        val netejar = findViewById<Button>(R.id.button)


        netejar.setOnClickListener {
            marca.text = null
            model.text = null
            matricula.text = null
            any.text = null
        }

    }

    fun saveInfo() {
        val marca = findViewById<EditText>(R.id.marca)
        val model = findViewById<EditText>(R.id.model)
        val matricula = findViewById<EditText>(R.id.matricula)
        val any = findViewById<EditText>(R.id.any)

        val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("marca", marca.text.toString())
        editor.putString("model", model.text.toString())
        editor.putString("matricula", matricula.text.toString())
        editor.putInt("any", any.text.toString().toInt())

        editor.putBoolean("info_saved", true)
        editor.apply()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.seguent -> {
                saveInfo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}