package dam2.amoreno.moreno_aniol_activitat8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val students = intent.getSerializableExtra("students") as Students

        val image = findViewById<ImageView>(R.id.foto)
        val email = findViewById<TextView>(R.id.emailResposta)
        val telefon = findViewById<TextView>(R.id.telefonResposta)
        val dni = findViewById<TextView>(R.id.dniResposta)
        val repetidor = findViewById<TextView>(R.id.repetidorResposta)
        val tornar = findViewById<Button>(R.id.tornar)

        image.setImageResource(students.foto)
        email.text = students.email
        telefon.text = students.telefon
        dni.text = students.dni
        repetidor.text = students.repetidor

        tornar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        supportActionBar?.title = students.nom
    }
}