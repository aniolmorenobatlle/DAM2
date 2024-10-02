package com.example.a3_music

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.a3_music.model.Disc6

class SixthDisc : AppCompatActivity() {

    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth_disc)

        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        val imageViewPlay = findViewById<ImageView>(R.id.imageViewPlay)
        val imageViewStop = findViewById<ImageView>(R.id.imageViewStop)

        val editTextSong = findViewById<EditText>(R.id.textViewSong)
        val editTextArtist = findViewById<EditText>(R.id.textViewArtist)
        val editTextYear = findViewById<EditText>(R.id.textViewYear)


        val mendes = Disc6("Shawn Mendes", "Wonder", "Wonder", 2022)


        editTextSong.setText(mendes.song)
        editTextArtist.setText(mendes.artist)
        editTextYear.setText(mendes.year.toString())

        imageViewBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this, R.raw.leave_me_alone)
            }
        }


        mediaPlayer = MediaPlayer.create(this, R.raw.wonder)

        imageViewPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        imageViewStop.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this, R.raw.wonder)
            }
        }


        var supportbar = mendes.song + " - " + mendes.name

        // navbar name
        supportActionBar?.title = supportbar

    }
}