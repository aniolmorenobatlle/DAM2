package dam2.amoreno.moreno_aniol_activitat11

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import dam2.amoreno.moreno_aniol_activitat11.adapter.Adapter
import dam2.amoreno.moreno_aniol_activitat11.classes.Products

class InvertaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventary)

        val add = findViewById<ImageView>(R.id.add)
        val list = findViewById<ImageView>(R.id.list)

        val marques = listOf("samsung", "mac", "logitech", "lenovo")

        val productList = mutableListOf<Products>()

        for (marca in marques) {
            val sharedPrefName = "shared_$marca"
            val sharedPref: SharedPreferences = getSharedPreferences(sharedPrefName, MODE_PRIVATE)

            val marcaStored = sharedPref.getString("marca", "")
            val modelStored = sharedPref.getString("model", "")
            val quantitatStored = sharedPref.getString("quantitat", "")
            val nomImatgeStored = sharedPref.getString("nomImatge", "")

            if (!marcaStored.isNullOrEmpty() && !modelStored.isNullOrEmpty() && !quantitatStored.isNullOrEmpty()) {
                val product = Products(marcaStored, modelStored, quantitatStored, nomImatgeStored ?: "")
                productList.add(product)
            }
        }

        val listView = findViewById<ListView>(R.id.listViewItems)
        val adapter = Adapter(this, productList)
        listView.adapter = adapter


        add.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        list.setOnClickListener {
            val intent = Intent(this, InvertaryActivity::class.java)
            startActivity(intent)
        }
    }
}