package dam2.amoreno.resum_uf1

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ActivityMusic : AppCompatActivity() {

    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val stop = findViewById<Button>(R.id.stop)
        val play = findViewById<Button>(R.id.play)

        mediaPlayer = MediaPlayer.create(this, R.raw.the_scientist)

        play.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        stop.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this, R.raw.the_scientist)
            }
        }

        supportActionBar?.title = "Music"
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.spinner -> {
                if (mediaPlayer.isPlaying) mediaPlayer.stop()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.music -> {
                val intent = Intent(this, ActivityMusic::class.java)
                startActivity(intent)
                true
            }
            R.id.intent -> {
                val intent = Intent(this, ActivityIntent::class.java)
                startActivity(intent)
                true
            }
            R.id.shared -> {
                val intent = Intent(this, ActivityShared::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}