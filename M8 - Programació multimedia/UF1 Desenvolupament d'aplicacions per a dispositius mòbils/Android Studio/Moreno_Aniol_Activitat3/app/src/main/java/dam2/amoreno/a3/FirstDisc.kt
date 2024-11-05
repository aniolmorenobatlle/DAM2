package dam2.amoreno.a3

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.a3_music.model.Disc

class FirstDisc : AppCompatActivity() {

    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_disc)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.action_bar_color, theme)))


        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        val imageViewPlay = findViewById<ImageView>(R.id.imageViewPlay)
        val imageViewStop = findViewById<ImageView>(R.id.imageViewStop)

        val editTextSong = findViewById<EditText>(R.id.textViewSong)
        val editTextArtist = findViewById<EditText>(R.id.textViewArtist)
        val editTextYear = findViewById<EditText>(R.id.textViewYear)


        val nf = Disc("NF", "The Search", "Leave Me Alone", 2018)


        editTextSong.setText(nf.song)
        editTextArtist.setText(nf.artist)
        editTextYear.setText(nf.year.toString())

        imageViewBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this, R.raw.clinton)
            }
        }


        mediaPlayer = MediaPlayer.create(this, R.raw.clinton)
        mediaPlayer.start()

        imageViewPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        imageViewStop.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this, R.raw.clinton)
            }
        }

        var supportbar = nf.song + " - " + nf.name

        // navbar name
        supportActionBar?.title = supportbar


    }
}