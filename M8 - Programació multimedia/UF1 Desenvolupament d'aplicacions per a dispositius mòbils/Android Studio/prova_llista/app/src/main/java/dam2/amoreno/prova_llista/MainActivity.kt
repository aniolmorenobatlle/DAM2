package dam2.amoreno.prova_llista

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import dam2.amoreno.prova_llista.adapter.AdapterList
import dam2.amoreno.prova_llista.classes.ClassList

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: AdapterList
    private val valorations = mutableListOf<ClassList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)

        valorations.add(ClassList("Joan", "Bon producte", 10))
        valorations.add(ClassList("Maria", "Hola que tal", 10))
        valorations.add(ClassList("Pere", "Ok", 5))

        adapter = AdapterList(this, valorations)
        listView.adapter = adapter
    }
}