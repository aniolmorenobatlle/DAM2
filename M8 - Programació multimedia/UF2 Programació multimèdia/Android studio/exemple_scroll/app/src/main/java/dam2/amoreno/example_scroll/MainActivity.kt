package dam2.amoreno.example_scroll

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pelicules = findViewById<TextView>(R.id.pelicules)
        val series = findViewById<TextView>(R.id.series)
        val esports = findViewById<TextView>(R.id.esports)
        val directes = findViewById<TextView>(R.id.directes)
        val documentals = findViewById<TextView>(R.id.documentals)
        val anime = findViewById<TextView>(R.id.anime)


        pelicules.setOnClickListener{
            Toast.makeText(this, pelicules.text.toString(), Toast.LENGTH_SHORT).show()
        }

        series.setOnClickListener{
            Toast.makeText(this, series.text.toString(), Toast.LENGTH_SHORT).show()
        }

        esports.setOnClickListener{
            Toast.makeText(this, esports.text.toString(), Toast.LENGTH_SHORT).show()
        }

        directes.setOnClickListener{
            Toast.makeText(this, directes.text.toString(), Toast.LENGTH_SHORT).show()
        }

        documentals.setOnClickListener{
            Toast.makeText(this, documentals.text.toString(), Toast.LENGTH_SHORT).show()
        }

        anime.setOnClickListener{
            Toast.makeText(this, anime.text.toString(), Toast.LENGTH_SHORT).show()
        }



    }
}