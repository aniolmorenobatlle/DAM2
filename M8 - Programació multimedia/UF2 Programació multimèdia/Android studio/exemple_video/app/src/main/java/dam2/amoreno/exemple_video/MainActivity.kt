package dam2.amoreno.exemple_video

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val video = findViewById<VideoView>(R.id.videoView)
        val videoPath = "android.resource://" + packageName + "/" + R.raw.nuh_uh

        val uri : Uri = Uri.parse(videoPath)

        video.setVideoURI(uri)

        val mediaController = MediaController(this)

        video.setMediaController(mediaController)
        mediaController.setAnchorView(video)

        video.requestFocus()

        video.start()
        video.setZOrderOnTop(true)
    }
}