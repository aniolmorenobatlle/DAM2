package dam2.amoreno.moreno_aniol_activitat9

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import dam2.amoreno.moreno_aniol_activitat9.Adapter.Adapter
import dam2.amoreno.moreno_aniol_activitat9.Classes.Students


class MainActivity : AppCompatActivity() {

    private lateinit var llista: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        llista = findViewById(R.id.llista)

        val llistaStudents: MutableList<Students> = mutableListOf(
            Students(
                R.drawable.yearbook,
                "Aniol",
                "Hola que tal estàs?",
                "12:00"
            ),
            Students(
                R.drawable.camera,
                "Johan",
                "Bona tarda senyor",
                "01:02"
            ),
            Students(
                R.drawable.yearbook,
                "Aniol",
                "Hola que tal estàs?",
                "13:15"
            ),
            Students(
                R.drawable.camera,
                "Johan",
                "Bona tarda senyor",
                "16:49"
            ),
            Students(
                R.drawable.yearbook,
                "Aniol",
                "Hola que tal estàs?",
                "20:00"
            ),
            Students(
                R.drawable.camera,
                "Johan",
                "Bona tarda senyor",
                "21:30"
            ),
            Students(
                R.drawable.yearbook,
                "Aniol",
                "Hola que tal estàs?",
                "23:45"
            ),
            Students(
                R.drawable.camera,
                "Johan",
                "Bona tarda senyor",
                "00:00"
            ),
            Students(
                R.drawable.yearbook,
                "Aniol",
                "Hola que tal estàs?",
                "01:02"
            ),
            Students(
                R.drawable.camera,
                "Johan",
                "Bona tarda senyor",
                "02:30"
            )
        )

        val searchView = findViewById<SearchView>(R.id.search)
        val adapter = Adapter(this, llistaStudents)
        llista.adapter = adapter


        val updating = findViewById<ImageView>(R.id.updating)
        val phone = findViewById<ImageView>(R.id.phone)
        val community = findViewById<ImageView>(R.id.community)
        val chat = findViewById<ImageView>(R.id.chat)
        val settings = findViewById<ImageView>(R.id.settings)
        val chatfloat = findViewById<ImageView>(R.id.chatfloat)

        updating.setOnClickListener {
            toastMessage()
        }

        phone.setOnClickListener {
            toastMessage()
        }

        community.setOnClickListener {
            toastMessage()
        }

        chat.setOnClickListener {
            toastMessage()
        }

        settings.setOnClickListener {
            toastMessage()
        }

        chatfloat.setOnClickListener {
            toastMessage()
        }



        // Filtra la llista segons el text introduit
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })


        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    // Toast message
    private fun toastMessage() {
        Toast.makeText(this, "Aquesta funció encara no està operativa", Toast.LENGTH_SHORT).show()
    }

    // Open camera
    private fun openCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }


    // Menu opcions
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.whatsapp, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                toastMessage()
                true
            }

            R.id.camera -> {
                openCamera()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}