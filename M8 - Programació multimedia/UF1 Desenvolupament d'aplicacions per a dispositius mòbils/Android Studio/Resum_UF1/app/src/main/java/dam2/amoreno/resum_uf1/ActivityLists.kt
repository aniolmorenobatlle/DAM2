package dam2.amoreno.resum_uf1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import dam2.amoreno.resum_uf1.Adapter.AdapterList
import dam2.amoreno.resum_uf1.Classes.Valorations


class ActivityLists : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: AdapterList
    private val valorations = mutableListOf<Valorations>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)


        listView = findViewById(R.id.list)


        valorations.add(Valorations("Joan", "Bon producte", 10))
        valorations.add(Valorations("Maria", "M'esperava més però està bé", 6))
        valorations.add(Valorations("Pau", "No es el que esperava", 2))

        adapter = AdapterList(this, valorations)
        listView.adapter = adapter

        supportActionBar?.title = "Llistes"
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.spinner -> {
                val intent = Intent(this, ActivityLists::class.java)
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