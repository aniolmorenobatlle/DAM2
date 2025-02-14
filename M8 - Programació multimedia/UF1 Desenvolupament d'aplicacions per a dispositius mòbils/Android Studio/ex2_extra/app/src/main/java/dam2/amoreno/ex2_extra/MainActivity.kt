package dam2.amoreno.ex2_extra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dam2.amoreno.ex2_extra.adapter.AdapterList
import dam2.amoreno.ex2_extra.classes.ClassList

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: AdapterList
    private val valorations = mutableListOf<ClassList>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textResult1 = findViewById<TextView>(R.id.textResult1)
        val textResult2 = findViewById<TextView>(R.id.textResult2)
        val textResult3 = findViewById<TextView>(R.id.textResult3)
        val textResult4 = findViewById<TextView>(R.id.textResult4)
        val imageView = findViewById<ImageView>(R.id.imageView)

        listView = findViewById(R.id.llista)

        valorations.add(ClassList(R.drawable.alumne1, "Cristina", "Pérez Garcia", 24, "Calonge"))
        valorations.add(ClassList(R.drawable.alumne2, "Antoni", "Planes Pons", 19, "Palamós"))

        adapter = AdapterList(this, valorations)
        listView.adapter = adapter


        val intentResult1 = intent.getStringExtra("textResult1Intent")
        val intentResult2 = intent.getStringExtra("textResult2Intent")
        val intentResult3 = intent.getStringExtra("textResult3Intent")
        val intentResult4 = intent.getStringExtra("textResult4Intent")
        val intentImatge = intent.getIntExtra("imageViewIntent", 0)

        textResult1.text = intentResult1
        textResult2.text = intentResult2
        textResult3.text = intentResult3
        textResult4.text = intentResult4
        imageView.setImageResource(intentImatge)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.alumne1 -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("textResult1Intent", valorations[0].nom)
                intent.putExtra("textResult2Intent", valorations[0].cognoms)
                intent.putExtra("textResult3Intent", valorations[0].edat.toString())
                intent.putExtra("textResult4Intent", valorations[0].poblacio)
                intent.putExtra("imageViewIntent", valorations[0].foto)
                startActivity(intent)
                true
            }
            R.id.alumne2 -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("textResult1Intent", valorations[1].nom)
                intent.putExtra("textResult2Intent", valorations[1].cognoms)
                intent.putExtra("textResult3Intent", valorations[1].edat.toString())
                intent.putExtra("textResult4Intent", valorations[1].poblacio)
                intent.putExtra("imageViewIntent", valorations[1].foto)
                startActivity(intent)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}