package dam2.amoreno.a5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val textViewName = findViewById<TextView>(R.id.textViewName)
        val imageViewCity = findViewById<ImageView>(R.id.imageViewCity)


        val sharedPref: SharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)

        val savedName = sharedPref.getString("name", "Default Name")
        val savedCity = sharedPref.getString("citys", "Default City")

        // Mostrem el nom al TextView
        textViewName.text = savedName


        when(savedCity) {
            "Estartit" -> imageViewCity.setImageResource(R.drawable.estartit)
            "Torroella de Montgrí" -> imageViewCity.setImageResource(R.drawable.torroella)
            "Palamós" -> imageViewCity.setImageResource(R.drawable.palamos)
            else -> imageViewCity.setImageResource(R.drawable.ic_launcher_foreground)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.first_screen -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.second_screen -> {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.third_screen -> {
                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.fourth_screen -> {
                val intent = Intent(this, FourthActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}