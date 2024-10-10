package com.example.uf1_a5

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        val yellowColor = ContextCompat.getColor(this, R.color.yellow)

        // Convertir el color a format hexadecimal
        val colorHex = String.format("#%06X", 0xFFFFFF and yellowColor)

        val sharedPref: SharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)

        val savedName = sharedPref.getString("name", "Default Name")
        val savedCity = sharedPref.getString("citys", "Default City")

        val nameWithColor = "<font color='$colorHex'>$savedName</font>"
        val cityWithColor = "<font color='$colorHex'>${savedCity}</font>"

        val formattedText = "Moltes gràcies $nameWithColor de $cityWithColor per utilitzar l'aplicació."

        // Aplicar el text al TextView
        val textViewGracies = findViewById<TextView>(R.id.textViewGracies)
        textViewGracies.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)
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