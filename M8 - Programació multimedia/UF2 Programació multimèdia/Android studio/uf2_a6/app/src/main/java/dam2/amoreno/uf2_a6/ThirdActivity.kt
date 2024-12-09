package dam2.amoreno.uf2_a6

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dam2.amoreno.uf2_a6.model.Users

class ThirdActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        database = FirebaseDatabase.getInstance().getReference("users")

        val username = findViewById<TextView>(R.id.username)

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var lastUser: Users? = null

                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue(Users::class.java)
                    lastUser = user // Guardar ultim usuari iterat
                }

                lastUser?.let {
                    val usernameDB = it.username
                    username.text = "Hola " + usernameDB
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })
    }
}