package dam2.amoreno.ex1_extra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textResult1 = findViewById<TextView>(R.id.textResult1)
        val textResult2 = findViewById<TextView>(R.id.textResult2)
        val textResult3 = findViewById<TextView>(R.id.textResult3)

        val paraula = intent.getStringExtra("paraula")
        val nombre1 = intent.getIntExtra("nombre1", 0)
        val nombre2 = intent.getIntExtra("nombre2", 0)


        textResult1.text = paraula
        textResult2.text = nombre1.toString()
        textResult3.text = nombre2.toString()
    }
}