package dam2.amoreno.uf2_ex2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val latitud = findViewById<EditText>(R.id.latitud)
        val longitut = findViewById<EditText>(R.id.longitut)
        val origen = findViewById<Button>(R.id.origen)
        val desti = findViewById<Button>(R.id.desti)
        val eliminar = findViewById<Button>(R.id.eliminar)
        val eliminar2 = findViewById<Button>(R.id.eliminar2)
        val mapa = findViewById<Button>(R.id.mapa)


        origen.setOnClickListener {
            val sharedPreferences = getSharedPreferences("origen", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putFloat("lat", latitud.text.toString().toFloat())
            editor.putFloat("long", longitut.text.toString().toFloat())
            editor.apply()

            Toast.makeText(this, "Origen Guardat", Toast.LENGTH_SHORT).show()

            longitut.text = null
            latitud.text = null
        }

        desti.setOnClickListener {
            val sharedPreferences = getSharedPreferences("desti", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putFloat("lat", latitud.text.toString().toFloat())
            editor.putFloat("long", longitut.text.toString().toFloat())
            editor.apply()

            Toast.makeText(this, "Destí Guardat", Toast.LENGTH_SHORT).show()

            longitut.text = null
            latitud.text = null
        }

        eliminar.setOnClickListener {
            longitut.text = null
            latitud.text = null
        }

        eliminar2.setOnClickListener {
            longitut.text = null
            latitud.text = null
        }


        mapa.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

    }
}