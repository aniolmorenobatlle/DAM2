package dam2.amoreno.moreno_aniol_activitat11

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref: SharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val marcaProducte = findViewById<Spinner>(R.id.marcaProducte)
        val modelProducte = findViewById<EditText>(R.id.modelProducte)
        val quantitatProducte = findViewById<Spinner>(R.id.quantitatProducte)
        val botoImatge = findViewById<Button>(R.id.botoImatge)
        val imatgeProducte = findViewById<ImageView>(R.id.imatgeProducte)
        val guardarProducte = findViewById<Button>(R.id.guardarProducte)

        // Comprova si hi ha una URI d'imatge passada
        val imageUri = intent.getStringExtra("imageUri")
        val imageName = intent.getStringExtra("imageName")
        if (imageUri != null) {
            imatgeProducte.setImageURI(imageUri.toUri())
        }

        guardarProducte.setOnClickListener {
            editor.putString("marca", marcaProducte.selectedItem.toString())
            editor.putString("model", modelProducte.text.toString())
            editor.putString("quantitat", quantitatProducte.selectedItem.toString())
            editor.putString("nom-imatge", imageName)

            editor.apply()
        }

        botoImatge.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }

}