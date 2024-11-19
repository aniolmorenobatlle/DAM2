package dam2.amoreno.uf2_a1

import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titol = findViewById<TextView>(R.id.textViewTitol)
        val any = findViewById<TextView>(R.id.textViewAny)
        val descripcio = findViewById<TextView>(R.id.textViewDescripcio)

        val babyDriver = findViewById<ImageView>(R.id.imageViewBabyDriver)
        val httyd = findViewById<ImageView>(R.id.imageViewHttyd)
        val dark_knight = findViewById<ImageView>(R.id.imageViewTheDarkKnight)
        val sw = findViewById<ImageView>(R.id.imageViewSW3)
        val toyStory = findViewById<ImageView>(R.id.imageViewToyStory)
        val rotr = findViewById<ImageView>(R.id.imageViewLOTR)

        val video = findViewById<VideoView>(R.id.videoView)


        babyDriver.setOnClickListener {
            titol.text = createBoldLabel("Títol:", "Baby Driver")
            any.text = createBoldLabel("Any:", "2017")
            descripcio.text = createBoldLabel(
                "Descripció:",
                "Baby Driver és una pel·lícula de 2017 dirigida per Edgar Wright. La pel·lícula està protagonitzada per Ansel Elgort, Kevin Spacey, Lily James, Jon Bernthal, Eiza González, Jon Hamm i Jamie Foxx."
            )

            val videoPath = "android.resource://" + packageName + "/" + R.raw.baby_driver
            val uri : Uri = Uri.parse(videoPath)
            video.setVideoURI(uri)
            video.start()
            val mediaController = MediaController(this)
            video.setMediaController(mediaController)
        }

        httyd.setOnClickListener {
            titol.text = createBoldLabel("Títol:", "How to Train Your Dragon")
            any.text = createBoldLabel("Any:", "2010")
            descripcio.text = createBoldLabel(
                "Descripció:",
                "How to Train Your Dragon és una pel·lícula d'animació de 2010 dirigida per Chris Sanders i Dean DeBlois. La pel·lícula està basada en la sèrie de llibres del mateix nom de Cressida Cowell."
            )

            val videoPath = "android.resource://" + packageName + "/" + R.raw.httyd
            val uri : Uri = Uri.parse(videoPath)
            video.setVideoURI(uri)
            video.start()
            val mediaController = MediaController(this)
            video.setMediaController(mediaController)
        }

        dark_knight.setOnClickListener {
            titol.text = createBoldLabel("Títol:", "The Dark Knight")
            any.text = createBoldLabel("Any:", "2008")
            descripcio.text = createBoldLabel(
                "Descripció:",
                "The Dark Knight és una pel·lícula de 2008 dirigida per Christopher Nolan. La pel·lícula està protagonitzada per Christian Bale, Heath Ledger, Aaron Eckhart, Michael Caine, Maggie Gyllenhaal, Gary Oldman i Morgan Freeman."
            )

            val videoPath = "android.resource://" + packageName + "/" + R.raw.dark_night
            val uri : Uri = Uri.parse(videoPath)
            video.setVideoURI(uri)
            video.start()
            val mediaController = MediaController(this)
            video.setMediaController(mediaController)
        }

        sw.setOnClickListener {
            titol.text = createBoldLabel("Títol:", "Star Wars: Episode III - Revenge of the Sith")
            any.text = createBoldLabel("Any:", "2005")
            descripcio.text = createBoldLabel(
                "Descripció:",
                "Star Wars: Episode III - Revenge of the Sith és una pel·lícula de 2005 dirigida per George Lucas. La pel·lícula està protagonitzada per Ewan McGregor, Natalie Portman, Hayden Christensen, Ian McDiarmid, Samuel L. Jackson, Christopher Lee i Anthony Daniels."
            )

            val videoPath = "android.resource://" + packageName + "/" + R.raw.sw3
            val uri : Uri = Uri.parse(videoPath)
            video.setVideoURI(uri)
            video.start()
            val mediaController = MediaController(this)
            video.setMediaController(mediaController)
        }

        toyStory.setOnClickListener {
            titol.text = createBoldLabel("Títol:", "Toy Story")
            any.text = createBoldLabel("Any:", "1995")
            descripcio.text = createBoldLabel(
                "Descripció:",
                "Toy Story és una pel·lícula d'animació de 1995 dirigida per John Lasseter. La pel·lícula està protagonitzada per Tom Hanks, Tim Allen, Don Rickles, Jim Varney, Wallace Shawn, John Ratzenberger i Annie Potts."
            )

            val videoPath = "android.resource://" + packageName + "/" + R.raw.baby_driver
            val uri : Uri = Uri.parse(videoPath)
            video.setVideoURI(uri)
            video.start()
            val mediaController = MediaController(this)
            video.setMediaController(mediaController)
        }

        rotr.setOnClickListener {
            titol.text = createBoldLabel("Títol:", "The Lord of the Rings: The Return of the King")
            any.text = createBoldLabel("Any:", "2003")
            descripcio.text = createBoldLabel(
                "Descripció:",
                "The Lord of the Rings: The Return of the King és una pel·lícula de 2003 dirigida per Peter Jackson. La pel·lícula està protagonitzada per Elijah Wood, Ian McKellen, Liv Tyler i Viggo Mortensen."
            )

            val videoPath = "android.resource://" + packageName + "/" + R.raw.httyd
            val uri : Uri = Uri.parse(videoPath)
            video.setVideoURI(uri)
            video.start()
            val mediaController = MediaController(this)
            video.setMediaController(mediaController)
        }
    }

    private fun createBoldLabel(label: String, text: String): SpannableString {
        val spannableString = SpannableString("$label $text")
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, label.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}