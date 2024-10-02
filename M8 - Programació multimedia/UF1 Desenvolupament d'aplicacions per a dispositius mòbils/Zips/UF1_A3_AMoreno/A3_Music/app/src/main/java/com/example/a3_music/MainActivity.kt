package com.example.a3_music

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setCustomView(R.layout.action_bar)
        supportActionBar?.setDisplayShowCustomEnabled(true)

        val imageViewNf = findViewById<ImageView>(R.id.imageViewNF)
        val imageViewClinton = findViewById<ImageView>(R.id.imageViewClinton)
        val imageViewMiller = findViewById<ImageView>(R.id.imageViewMiller)
        val imageViewRocky = findViewById<ImageView>(R.id.imageViewRocky)
        val imageViewColdplay = findViewById<ImageView>(R.id.imageViewColdplay)
        val imageViewMendes = findViewById<ImageView>(R.id.imageViewMendes)


        imageViewNf.setOnClickListener {
            val intent = Intent(this, FirstDisc::class.java)
            startActivity(intent)
        }

        imageViewClinton.setOnClickListener {
            val intent2 = Intent(this, SecondDisc::class.java)
            startActivity(intent2)
        }

        imageViewMiller.setOnClickListener {
            val intent3 = Intent(this, ThirdDisc::class.java)
            startActivity(intent3)
        }

        imageViewRocky.setOnClickListener {
            val intent4 = Intent(this, FourthDisc::class.java)
            startActivity(intent4)
        }

        imageViewColdplay.setOnClickListener {
            val intent5 = Intent(this, FifthDisc::class.java)
            startActivity(intent5)
        }

        imageViewMendes.setOnClickListener {
            val intent6 = Intent(this, SixthDisc::class.java)
            startActivity(intent6)
        }


    }
}