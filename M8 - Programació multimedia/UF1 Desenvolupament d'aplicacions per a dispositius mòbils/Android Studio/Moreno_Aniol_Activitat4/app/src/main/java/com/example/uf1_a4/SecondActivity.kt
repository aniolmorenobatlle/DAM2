package com.example.uf1_a4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val editTextName = intent.getStringExtra("name")
        val spinnerCitys = intent.getStringExtra("citys")
        val editTextNum1 = intent.getIntExtra("num1", 0)
        val editTextNum2 = intent.getIntExtra("num2", 0)

        val textViewName = findViewById<TextView>(R.id.textViewName)
        val imageViewCity = findViewById<ImageView>(R.id.imageViewCity)

        textViewName.text = editTextName.toString()


        when(spinnerCitys) {
            "Estartit" -> imageViewCity.setImageResource(R.drawable.estartit)
            "Torroella de Montgrí" -> imageViewCity.setImageResource(R.drawable.torroella)
            "Palamós" -> imageViewCity.setImageResource(R.drawable.palamos)
            else -> imageViewCity.setImageResource(R.drawable.ic_launcher_foreground)
        }


        val imageViewNext = findViewById<ImageView>(R.id.imageViewNext)
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)

        imageViewBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        imageViewNext.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java).apply {
                putExtra("name", editTextName)
                putExtra("citys", spinnerCitys)
                putExtra("num1", editTextNum1)
                putExtra("num2", editTextNum2)
            }
            startActivity(intent)
        }

    }
}