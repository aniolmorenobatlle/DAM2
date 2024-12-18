package dam2.amoreno.uf2_a6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dam2.amoreno.uf2_a6.adapter.HousesListAdapter
import dam2.amoreno.uf2_a6.classes.House
import dam2.amoreno.uf2_a6.classes.HousesList

class RecycleActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var housesAdapter: HousesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)

        val sharedPreferences = getSharedPreferences("houses", Context.MODE_PRIVATE)
        val gson = Gson()
        val currentHousesJson = sharedPreferences.getString("houses_list", "[]")

        // Definir el tipus de la llista
        val houseListType = object : TypeToken<MutableList<House>>() {}.type

        // JSON a llista
        val houseList = gson.fromJson<MutableList<House>>(currentHousesJson, houseListType)

        // Convertir perque el adapter ho pugui llegir
        val housesList = houseList.map { house ->
            HousesList(
                house.address,
                house.price,
                resources.getIdentifier(house.imageName, "drawable", packageName),
                house.lat,
                house.long
            )
        }.toMutableList()

        // Configura el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Assigna l'adaptador amb la llista de cases
        housesAdapter = HousesListAdapter(housesList) { selectedHouse ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("address", selectedHouse.address)
                putExtra("price", selectedHouse.price)
                putExtra("image", selectedHouse.imageResourceId)
                putExtra("lat", selectedHouse.lat)
                putExtra("long", selectedHouse.long)
            }

            startActivity(intent)
        }

        recyclerView.adapter = housesAdapter

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