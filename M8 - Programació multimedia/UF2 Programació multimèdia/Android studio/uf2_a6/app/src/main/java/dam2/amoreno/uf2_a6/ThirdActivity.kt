package dam2.amoreno.uf2_a6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dam2.amoreno.uf2_a6.Adapter.SliderAdapter
import dam2.amoreno.uf2_a6.Model.Users
class ThirdActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    lateinit var viewPager: ViewPager2
    lateinit var sliderAdapter: SliderAdapter

    val imageList = listOf(
        R.drawable.estartit2,
        R.drawable.estartit3,
        R.drawable.estartit4,
        R.drawable.estartit5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val sharedPrefHouses = getSharedPreferences("houses", MODE_PRIVATE)

        database = FirebaseDatabase.getInstance().getReference("users")

        val username = findViewById<TextView>(R.id.username)
        val estartit = findViewById<ImageView>(R.id.estartit)
        val pals = findViewById<ImageView>(R.id.pals)
        val palamos = findViewById<ImageView>(R.id.palamos)
        val calonge = findViewById<ImageView>(R.id.calonge)
        val girona = findViewById<ImageView>(R.id.girona)
        val sell = findViewById<Button>(R.id.sell)
        val rent = findViewById<Button>(R.id.rent)
        val price = findViewById<Button>(R.id.price)
        val house = findViewById<Button>(R.id.house)
        val add = findViewById<ImageView>(R.id.add)
        val see = findViewById<ImageView>(R.id.see)
        val home = findViewById<ImageView>(R.id.home)


        estartit.setOnClickListener { showUnavailableFeatureToast() }
        pals.setOnClickListener { showUnavailableFeatureToast() }
        palamos.setOnClickListener { showUnavailableFeatureToast() }
        calonge.setOnClickListener { showUnavailableFeatureToast() }
        girona.setOnClickListener { showUnavailableFeatureToast() }
        sell.setOnClickListener { showUnavailableFeatureToast() }
        rent.setOnClickListener { showUnavailableFeatureToast() }
        price.setOnClickListener { showUnavailableFeatureToast() }
        house.setOnClickListener { showUnavailableFeatureToast() }


        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var lastUser: Users? = null

                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(Users::class.java)
                    lastUser = user // Guardar ultim usuari iterat
                }

                lastUser?.let {
                    val usernameDB = it.username
                    username.text = "Hola, " + usernameDB + "!"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })

        // Inicialitzar slider
        viewPager = findViewById(R.id.slider)
        sliderAdapter = SliderAdapter(this, imageList)
        viewPager.adapter = sliderAdapter

        // Mètode per gestionar l'slider
        startImageSlideshow()


        add.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

//        see.setOnClickListener {
//            val intent = Intent(this, InvertaryActivity::class.java)
//            startActivity(intent)
//        }

        home.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showUnavailableFeatureToast() {
        Toast.makeText(this, "Funció encara no disponible", Toast.LENGTH_SHORT).show()
    }

    private fun startImageSlideshow() {

        // Handler per executar tasques amb delay
        val handler = android.os.Handler()

        // Codi per tal d'executar un fil
        val runnable = object : Runnable {

            // inicializtem la variable per mostrar la imatge actual
            var currentPage = 0

            // Codi que es crida cada cop que es llança el handler
            override fun run() {
                // Mostra la imatge actual
                viewPager.setCurrentItem(currentPage, true)

                // S'avança a la següent imatge
                currentPage++

                // Es passa de l'última imatge a la primera
                if (currentPage >= imageList.size) {
                    currentPage = 0
                }

                // Cada 3 segons es llança el codi anterior
                handler.postDelayed(this, 5000)
            }
        }
        // El procés s'inicia als 3 segons
        handler.postDelayed(runnable, 5000)
    }
}