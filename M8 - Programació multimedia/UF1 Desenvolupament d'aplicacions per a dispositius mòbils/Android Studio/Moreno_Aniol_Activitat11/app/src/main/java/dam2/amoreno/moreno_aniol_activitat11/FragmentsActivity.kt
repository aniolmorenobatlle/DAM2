package dam2.amoreno.moreno_aniol_activitat11

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import dam2.amoreno.moreno_aniol_activitat11.fragments.FragmentDescription
import dam2.amoreno.moreno_aniol_activitat11.fragments.FragmentImage

class FragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        val add = findViewById<ImageView>(R.id.add)
        val list = findViewById<ImageView>(R.id.list)

        val buttonImage = findViewById<Button>(R.id.buttonImage)
        val buttonDescription = findViewById<Button>(R.id.buttonDescription)

        val fragmentImage = FragmentImage()
        val fragmentDescription = FragmentDescription()

        val marca = intent.getStringExtra("marca")
        val model = intent.getStringExtra("model")
        val quantitat = intent.getStringExtra("quantitat")
        val nomImatge = intent.getStringExtra("nomImatge")

        val bundle = Bundle().apply {
            putString("marca", marca)
            putString("model", model)
            putString("quantitat", quantitat)
            putString("nomImatge", nomImatge)
        }

        fragmentImage.arguments = bundle
        fragmentDescription.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentImage, fragmentImage)
            .commit()

        buttonImage.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentImage, fragmentImage)
                .commit()
            findViewById<View>(R.id.fragmentDescription).visibility = View.GONE
            findViewById<View>(R.id.fragmentImage).visibility = View.VISIBLE
        }

        buttonDescription.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentDescription, fragmentDescription)
                .commit()
            findViewById<View>(R.id.fragmentImage).visibility = View.GONE
            findViewById<View>(R.id.fragmentDescription).visibility = View.VISIBLE
        }

        findViewById<View>(R.id.fragmentDescription).visibility = View.GONE


        add.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        list.setOnClickListener {
            val intent = Intent(this, InvertaryActivity::class.java)
            startActivity(intent)
        }
    }
}
