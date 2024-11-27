package dam2.amoreno.a4_uf2_aniol_moreno

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.model.Contact
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        // TODO : generar un formulari que permeti introduir un nou contacte (id, nom, població i posició -long, lat-) a la base de dadesp
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        val addFirebase: Button = findViewById(R.id.button)

        // Iniciem la base de dades
        database = FirebaseDatabase.getInstance().getReference("contacts")

        // Mètode per agafar les dades del firebase
        fetchContacts()

        // Botó per afegir un contacte a la base de dades
        addFirebase.setOnClickListener {
            // Creem en nou contacte
            val nouRegistre = Contact(
                id = 3,
                name = "Anonti Lopez",
                address = "Pals",
                position = listOf("42.0° N", "2.0° E")
            )

            val contactId = database.push().key
            if (contactId != null) {
                database.child(contactId).setValue(nouRegistre)
            }
        }
    }

    private fun fetchContacts() {
        // Obtenim les dades
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            // Es retornen dades
            override fun onDataChange(snapshot: DataSnapshot) {
                // Recorrem cada document "registre" dintre de la "taula"
                for (contactSnapshot in snapshot.children) {
                    // Convertim el "registre" actual a un objecte Contact
                    val contact = contactSnapshot.getValue(Contact::class.java)
                    if (contact != null) {
                        // Assignem el nom del contacte al text view
                        textView.text = contact.name;
                    }
                }
            }
            // Si error
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "No connexió", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
