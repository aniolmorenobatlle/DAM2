package dam2.amoreno.a5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)


        val textViewResultat1 = findViewById<TextView>(R.id.textViewResultat1)
        val textViewResultat2 = findViewById<TextView>(R.id.textViewResultat2)
        val textViewResultatSuma = findViewById<TextView>(R.id.textViewResultatSuma)
        val textViewResultatResta = findViewById<TextView>(R.id.textViewResultatResta)


        val sharedPref: SharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)

        val savedNum1 = sharedPref.getInt("num1", 0)
        val savedNum2 = sharedPref.getInt("num2", 0)

        textViewResultat1.text = savedNum1.toString()
        textViewResultat2.text = savedNum2.toString()

        textViewResultatSuma.text = (savedNum1 + savedNum2).toString()
        textViewResultatResta.text = (savedNum1 - savedNum2).toString()
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