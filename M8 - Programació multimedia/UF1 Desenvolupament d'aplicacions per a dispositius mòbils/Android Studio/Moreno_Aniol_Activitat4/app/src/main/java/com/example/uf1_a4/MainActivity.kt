package com.example.uf1_a4

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
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


        val imageViewNext = findViewById<ImageView>(R.id.imageViewNext)

        imageViewNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("name", editTextName.text.toString())
                putExtra("citys", spinnerCitys.selectedItem.toString())
                putExtra("num1", editTextNum1.text.toString().toInt())
                putExtra("num2", editTextNum2.text.toString().toInt())
            }

            startActivity(intent)
        }
    }
}