package com.example.a3_music

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.a3_music.model.Disc4

class FourthDisc : AppCompatActivity() {

    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth_disc)

        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        val imageViewPlay = findViewById<ImageView>(R.id.imageViewPlay)
        val imageViewStop = findViewById<ImageView>(R.id.imageViewStop)

        val editTextSong = findViewById<EditText>(R.id.textViewSong)
        val editTextArtist = findViewById<EditText>(R.id.textViewArtist)
        val editTextYear = findViewById<EditText>(R.id.textViewYear)


        val rocky = Disc4("ASAP ROCKY", "Sundress", "Sundress", 2021)


        editTextSong.setText(rocky.song)
        editTextArtist.setText(rocky.artist)
        editTextYear.setText(rocky.year.toString())

        imageViewBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this, R.raw.sundress)
            }
        }


        mediaPlayer = MediaPlayer.create(this, R.raw.sundress)

        imageViewPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        imageViewStop.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this, R.raw.sundress)
            }
        }

        var supportbar = rocky.song + " - " + rocky.name

        // navbar name
        supportActionBar?.title = supportbar

    }
}