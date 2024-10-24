package com.example.uf1_a4

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        val editTextName = intent.getStringExtra("name")
        val spinnerCitys = intent.getStringExtra("citys")



        val yellowColor = ContextCompat.getColor(this, R.color.yellow)

        // Convertir el color a format hexadecimal
        val colorHex = String.format("#%06X", 0xFFFFFF and yellowColor)

        val nameWithColor = "<font color='$colorHex'>$editTextName</font>"
        val cityWithColor = "<font color='$colorHex'>${spinnerCitys}</font>"

        val formattedText = "Moltes gràcies $nameWithColor de $cityWithColor per utilitzar l'aplicació."

        // Aplicar el text al TextView
        val textViewGracies = findViewById<TextView>(R.id.textViewGracies)
        textViewGracies.text = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)


        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)

        imageViewBack.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}