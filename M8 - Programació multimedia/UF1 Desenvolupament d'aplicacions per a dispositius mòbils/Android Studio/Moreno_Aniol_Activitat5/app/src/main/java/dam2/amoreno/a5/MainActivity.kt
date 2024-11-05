package dam2.amoreno.a5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editTextName = findViewById<EditText>(R.id.editTextName)
        val spinnerCitys = findViewById<Spinner>(R.id.spinnerCitys)
        val editTextNum1 = findViewById<EditText>(R.id.editTextNum1)
        val editTextNum2 = findViewById<EditText>(R.id.editTextNum2)
        val buttonSave = findViewById<Button>(R.id.buttonSave)


        val sharedPref: SharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)


        if (sharedPref.getBoolean("info_saved", false)) {
            buttonSave.isEnabled = false

            val savedName = sharedPref.getString("name", "")
            val savedCity = sharedPref.getString("citys", "")
            val savedNum1 = sharedPref.getInt("num1", 0)
            val savedNum2 = sharedPref.getInt("num2", 0)

            editTextName.setText(savedName)
            editTextNum1.setText(savedNum1.toString())
            editTextNum2.setText(savedNum2.toString())

            val cityAdapter = spinnerCitys.adapter
            for (i in 0 until cityAdapter.count) {
                if (cityAdapter.getItem(i).toString() == savedCity) {
                    spinnerCitys.setSelection(i)
                    break
                }
            }
        }


        buttonSave.setOnClickListener {
            val editor = sharedPref.edit()

            editor.putString("name", editTextName.text.toString())
            editor.putString("citys", spinnerCitys.selectedItem.toString())
            editor.putInt("num1", editTextNum1.text.toString().toInt())
            editor.putInt("num2", editTextNum2.text.toString().toInt())

            editor.putBoolean("info_saved", true)
            editor.apply()

            buttonSave.isEnabled = false

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
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