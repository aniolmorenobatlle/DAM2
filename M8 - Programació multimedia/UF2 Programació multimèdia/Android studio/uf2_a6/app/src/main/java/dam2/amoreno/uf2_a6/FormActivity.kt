package dam2.amoreno.uf2_a6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dam2.amoreno.uf2_a6.classes.House

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        val poblation = findViewById<Spinner>(R.id.spinner)
        val address = findViewById<EditText>(R.id.addressResult)
        val price = findViewById<EditText>(R.id.priceResult)
        val long = findViewById<EditText>(R.id.longitudResult)
        val lat = findViewById<EditText>(R.id.latitudResult)
        val send = findViewById<Button>(R.id.send)

        val imageNames = listOf(
            "estartit",
            "estartit2",
            "estartit3",
            "estartit4",
            "estartit5"
        )

        send.setOnClickListener {
            if (address.text.isNotEmpty() && price.text.isNotEmpty() && long.text.isNotEmpty() && lat.text.isNotEmpty()) {
                val house = House(
                    poblation = poblation.selectedItem.toString(),
                    address = address.text.toString(),
                    price = price.text.toString().toFloat(),
                    lat = lat.text.toString().toFloat(),
                    long = long.text.toString().toFloat(),
                    imageName = imageNames.random()
                )

                val sharedPreferences = getSharedPreferences("houses", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                val gson = Gson()

                // Obtenir la llista actual de cases des de SharedPreferences
                val currentCasesJson = sharedPreferences.getString("houses_list", "[]")
                val caseListType = object : TypeToken<MutableList<House>>() {}.type
                val caseList = gson.fromJson<MutableList<House>>(currentCasesJson, caseListType)

                caseList.add(house)

                // Convertir a JSON
                val newCasesJson = gson.toJson(caseList)

                try {
                    editor.putString("houses_list", newCasesJson)
                    editor.apply()

                    Toast.makeText(this, "Casa/apartament afegida correctament!!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, RecycleActivity::class.java)
                    startActivity(intent)

                } catch (e: Exception) {
                    Toast.makeText(this, "Error al afegir la casa/apartament", Toast.LENGTH_SHORT).show()
                }

            } else if (poblation.selectedItem.toString() == "Seleccionar") {
                Toast.makeText(this, "Selecciona una població", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Falten camps per omplir", Toast.LENGTH_SHORT).show()
            }
        }


        val add = findViewById<ImageView>(R.id.add)
        val see = findViewById<ImageView>(R.id.see)
        val home = findViewById<ImageView>(R.id.home)


        add.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        see.setOnClickListener {
            val intent = Intent(this, RecycleActivity::class.java)
            startActivity(intent)
        }

        home.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}