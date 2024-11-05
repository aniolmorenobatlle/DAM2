package dam2.amoreno.moreno_aniol_activitat8

import android.content.Intent
import android.os.Bundle
import android.text.style.UpdateLayout
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var llista: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llista = findViewById(R.id.llista)

        val llistaStudents: MutableList<Students> = mutableListOf(
            Students(
                R.drawable.yearbook1,
                "Aniol",
                "L'Estartit",
                "aniolmoreno@gmail.com",
                "56498765",
                "54547862G",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Johan",
                "Girona",
                "johan@gmail.com",
                "145263789",
                "578963218p",
                "No"
            )
        )

        val adapter = Adapter(this, llistaStudents)
        llista.adapter = adapter

        llista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("students", llistaStudents[position])
            startActivity(intent)
        }
    }

    // Incloure alumne
    fun includeOne() {
        val newStudent = Students(
            R.drawable.yearbook1,
            "Hola",
            "Girona",
            "hola@gmail.com",
            "98765432",
            "12345678A",
            "Sí"
        )

        (llista.adapter as Adapter).addStudent(newStudent)
    }


    // Incloure 15 alumnes
    fun includeAll() {
        val newStudents = listOf(
            Students(
                R.drawable.yearbook1,
                "Alumne 1",
                "Girona",
                "alumne1@gmail.com",
                "98765432",
                "12345678A",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Alumne 2",
                "Palamós",
                "alumne2@gmail.com",
                "98765433",
                "12345678B",
                "No"
            ),
            Students(
                R.drawable.yearbook1,
                "Alumne 3",
                "Madrid",
                "alumne3@gmail.com",
                "98765434",
                "12345678C",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Alumne 4",
                "Sevilla",
                "alumne4@gmail.com",
                "98765435",
                "12345678D",
                "No"
            ),
            Students(
                R.drawable.yearbook1,
                "Alumne 5",
                "València",
                "alumne5@gmail.com",
                "98765436",
                "12345678E",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Alumne 6",
                "Zaragoza",
                "alumne6@gmail.com",
                "98765437",
                "12345678F",
                "No"
            ),
            Students(
                R.drawable.yearbook1,
                "Alumne 7",
                "Bilbao",
                "alumne7@gmail.com",
                "98765438",
                "12345678G",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Alumne 8",
                "Màlaga",
                "alumne8@gmail.com",
                "98765439",
                "12345678H",
                "No"
            ),
            Students(
                R.drawable.yearbook1,
                "Alumne 9",
                "Granada",
                "alumne9@gmail.com",
                "98765440",
                "12345678I",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Alumne 10",
                "Vigo",
                "alumne10@gmail.com",
                "98765441",
                "12345678J",
                "No"
            ),
            Students(
                R.drawable.yearbook1,
                "Alumne 11",
                "Murcia",
                "alumne11@gmail.com",
                "98765442",
                "12345678K",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Alumne 12",
                "Palma",
                "alumne12@gmail.com",
                "98765443",
                "12345678L",
                "No"
            ),
            Students(
                R.drawable.yearbook1,
                "Alumne 13",
                "Girona",
                "alumne13@gmail.com",
                "98765444",
                "12345678M",
                "Sí"
            ),
            Students(
                R.drawable.yearbook2,
                "Alumne 14",
                "London",
                "alumne14@gmail.com",
                "98765445",
                "12345678N",
                "No"
            ),
            Students(
                R.drawable.yearbook1,
                "Alumne 15",
                "Oviedo",
                "alumne15@gmail.com",
                "98765446",
                "12345678O",
                "Sí"
            )
        )

        val adapter = llista.adapter as Adapter
        for (student in newStudents) {
            adapter.addStudent(student)
        }
    }


    // Esborrar ultim alumne
    fun clearLast() {
        val adapter = llista.adapter as Adapter
        val student = adapter.getItem(adapter.count - 1)
        adapter.remove(student)
        adapter.notifyDataSetChanged()
    }


    // Esborrar tots els alumnes
    fun clearAll() {
        (llista.adapter as Adapter).clear()
    }


    // Canviar el nom del primer alumne
    fun updateOne() {
        val adapter = llista.adapter as Adapter
        val student = adapter.getItem(0)
        student?.nom = "Pepito"
        adapter.notifyDataSetChanged()
    }


    // Menu opcions
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.IncludeOne -> {
                includeOne()
                true
            }

            R.id.IncluideAll -> {
                includeAll()
                true
            }

            R.id.ClearLast -> {
                clearLast()
                true
            }

            R.id.ClearAll -> {
                clearAll()
                true
            }

            R.id.UpdateOne -> {
                updateOne()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}