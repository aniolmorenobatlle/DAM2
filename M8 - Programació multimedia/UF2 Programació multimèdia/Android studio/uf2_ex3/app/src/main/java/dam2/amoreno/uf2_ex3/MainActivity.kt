package dam2.amoreno.uf2_ex3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import dam2.amoreno.uf2_ex3.classes.Cotxe
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marca = findViewById<EditText>(R.id.marca)
        val model = findViewById<EditText>(R.id.model)
        val preu = findViewById<EditText>(R.id.preu)
        val any = findViewById<EditText>(R.id.any)
        val lat = findViewById<EditText>(R.id.latitud)
        val long = findViewById<EditText>(R.id.longitut)
        val poblacio = findViewById<EditText>(R.id.poblacio)
        val afegir = findViewById<Button>(R.id.afegir)


        afegir.setOnClickListener {
            val cotxe = Cotxe(
                marca = marca.text.toString(),
                model = model.text.toString(),
                preu = preu.text.toString().toInt(),
                any = any.text.toString().toInt(),
                lat = lat.text.toString().toFloat(),
                long = long.text.toString().toFloat(),
                poblacio = poblacio.toString()
            )

            val sharedPreferences = getSharedPreferences("cotxes", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            val gson = Gson()

            val currentCotxesJson = sharedPreferences.getString("cotxes_list", "[]")
            val cotxeListType = object : TypeToken<MutableList<Cotxe>>() {}.type
            val cotxeList = gson.fromJson<MutableList<Cotxe>>(currentCotxesJson, cotxeListType)

            cotxeList.add(cotxe)

            val newCotxeJson = gson.toJson(cotxeList)

            try {
                editor.putString("cotxes_list", newCotxeJson)
                editor.apply()
                Toast.makeText(this, "Cotxe afegit correctament!!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(this, "Error al afegir el cotxe", Toast.LENGTH_SHORT).show()
            }
        }

    }
}