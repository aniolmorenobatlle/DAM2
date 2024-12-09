package dam2.amoreno.uf2_a6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dam2.amoreno.uf2_a6.model.Users

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().getReference("users")

        val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val usernameInput = findViewById<TextInputEditText>(R.id.usernameText)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordText)
        val register = findViewById<Button>(R.id.register)
        val checkIfExist = findViewById<TextView>(R.id.checkIfExist)

        if (sharedPref.getBoolean("registered", false)) {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
            finish()
        }

        register.setOnClickListener {
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (postSnapshot in snapshot.children) {
                        val user = postSnapshot.getValue(Users::class.java)
                        if (user?.username == usernameInput.text.toString()) {
                            checkIfExist.visibility = View.VISIBLE
                            return
                        } else {
                            val newUser = Users(
                                username = usernameInput.text.toString(),
                                password = passwordInput.text.toString()
                            )

                            val userId = database.push().key
                            if (userId != null) {
                                database.child(userId).setValue(newUser)
                            }

                            editor.putBoolean("registered", true)
                            editor.apply()

                            val intent = Intent(this@MainActivity, ThirdActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    checkIfExist.visibility = View.GONE
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }

            })


        }
    }
}