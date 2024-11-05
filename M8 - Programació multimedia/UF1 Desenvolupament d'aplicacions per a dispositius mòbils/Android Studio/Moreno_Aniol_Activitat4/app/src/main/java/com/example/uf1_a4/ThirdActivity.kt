package com.example.uf1_a4

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val editTextName = intent.getStringExtra("name")
        val spinnerCitys = intent.getStringExtra("citys")
        val editTextNum1 = intent.getIntExtra("num1", 0)
        val editTextNum2 = intent.getIntExtra("num2", 0)


        val textViewResultat1 = findViewById<TextView>(R.id.textViewResultat1)
        val textViewResultat2 = findViewById<TextView>(R.id.textViewResultat2)

        textViewResultat1.text = editTextNum1.toString()
        textViewResultat2.text = editTextNum2.toString()


        val textViewResultatSuma = findViewById<TextView>(R.id.textViewResultatSuma)
        val textViewResultatResta = findViewById<TextView>(R.id.textViewResultatResta)

        textViewResultatSuma.text = (editTextNum1 + editTextNum2).toString()
        textViewResultatResta.text = (editTextNum1 - editTextNum2).toString()


        val imageViewNext = findViewById<ImageView>(R.id.imageViewNext)
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)

        imageViewBack.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        imageViewNext.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java).apply {
                putExtra("name", editTextName)
                putExtra("citys", spinnerCitys)
            }
            startActivity(intent)
        }

    }
}