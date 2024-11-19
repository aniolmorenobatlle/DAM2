package dam2.amoreno.resum_uf1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import dam2.amoreno.resum_uf1.Fragments.FragmentImage
import dam2.amoreno.resum_uf1.Fragments.FragmentValorations

class ActivityFragments : AppCompatActivity() {

    private lateinit var editTextName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        val image = findViewById<Button>(R.id.imatge)
        val valorations = findViewById<Button>(R.id.valoracions)

        val fragmentImage = FragmentImage()
        val fragmentValorations = FragmentValorations()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragmentImage)
            .commit()

        image.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragmentImage)
                .commit()
        }

        valorations.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragmentValorations)
                .commit()
        }

        supportActionBar?.title = "Fragments"
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.spinner -> {
                val intent = Intent(this, ActivityFragments::class.java)
                startActivity(intent)
                true
            }
            R.id.music -> {
                val intent = Intent(this, ActivityMusic::class.java)
                startActivity(intent)
                true
            }
            R.id.intent -> {
                val intent = Intent(this, ActivityIntent::class.java).apply {
                    putExtra("name", editTextName.text.toString())
                }
                startActivity(intent)
                true
            }
            R.id.shared -> {
                val intent = Intent(this, ActivityShared::class.java)
                startActivity(intent)
                true
            }
            R.id.lists -> {
                val intent = Intent(this, ActivityLists::class.java)
                startActivity(intent)
                true
            }
            R.id.fragments -> {
                val intent = Intent(this, ActivityFragments::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}