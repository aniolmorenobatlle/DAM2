package dam2.amoreno.uf2_a4

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.model.Usuari
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // iniciar base de dades
        database = FirebaseDatabase.getInstance().getReference("usuari")

        val id = findViewById<EditText>(R.id.id)
        val nom = findViewById<EditText>(R.id.nom)
        val poblacio = findViewById<EditText>(R.id.poblacio)
        val longitud = findViewById<EditText>(R.id.longitut)
        val latitud = findViewById<EditText>(R.id.latitud)
        val enviar = findViewById<Button>(R.id.enviar)


        enviar.setOnClickListener {
            val nouUsuari = Usuari(
                id = id.text.toString().toInt(),
                nom = nom.text.toString(),
                poblacio = poblacio.text.toString(),
                position = listOf(longitud.text.toString(), latitud.text.toString())
            )

            val usuariId = database.push().key
            if (usuariId != null) {
                database.child(usuariId).setValue(nouUsuari)
            }

            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mapa -> {
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}