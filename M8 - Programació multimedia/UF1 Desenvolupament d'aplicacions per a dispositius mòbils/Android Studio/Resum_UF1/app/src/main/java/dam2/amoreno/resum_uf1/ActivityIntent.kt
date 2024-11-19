package dam2.amoreno.resum_uf1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityIntent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)


        val resultName = intent.getStringExtra("name")
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        textViewResult.text = resultName ?: "No s'ha introduït cap nom"

        supportActionBar?.title = "Intents"
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.spinner -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.music -> {
                val intent = Intent(this, ActivityMusic::class.java)
                startActivity(intent)
                true
            }
            R.id.intent -> {
                val intent = Intent(this, ActivityIntent::class.java)
                startActivity(intent)
                true
            }
            R.id.shared -> {
                val intent = Intent(this, ActivityShared::class.java)
                startActivity(intent)
                true
            }
            R.id.lists -> {
                val intent = Intent(this, ActivityLists::class.java)
                startActivity(intent)
                true
            }
            R.id.fragments -> {
                val intent = Intent(this, ActivityFragments::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}