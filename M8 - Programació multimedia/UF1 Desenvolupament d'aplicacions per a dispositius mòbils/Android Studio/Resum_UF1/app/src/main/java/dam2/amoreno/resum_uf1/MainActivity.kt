package dam2.amoreno.resum_uf1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val saveButton = findViewById<Button>(R.id.saveButton)
        val citySpinner = findViewById<Spinner>(R.id.citySpinner)
        editTextName = findViewById(R.id.editTextName)


        val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)

        fun enableButton() {
            val isCityEnabled = if (citySpinner.selectedItemPosition == 0) false else true

            if (isCityEnabled) {
                saveButton.isEnabled = true
            } else {
                saveButton.isEnabled = false
            }
        }


        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                enableButton()

                saveButton.setOnClickListener {
                    val editor = sharedPref.edit()

                    editor.putString("city", citySpinner.selectedItem.toString())

                    editor.putBoolean("info_saved", true)
                    editor.apply()

                    Toast.makeText(this@MainActivity, "City saved", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                saveButton.isEnabled = false
            }
        }

        supportActionBar?.title = "Spinner"
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
                val intent = Intent(this, ActivityIntent::class.java).apply {
                    putExtra("name", editTextName.text.toString())
                }
                startActivity(intent)
                true
            }
            R.id.shared -> {
                val intent = Intent(this, ActivityShared::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}