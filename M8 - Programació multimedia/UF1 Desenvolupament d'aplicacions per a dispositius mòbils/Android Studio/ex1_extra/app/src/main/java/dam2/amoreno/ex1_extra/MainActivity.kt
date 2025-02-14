package dam2.amoreno.ex1_extra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val spinner1 = findViewById<Spinner>(R.id.spinner)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val spinnerResult1 = spinner1.selectedItem.toString().toIntOrNull()
            val spinnerResult2 = spinner2.selectedItem.toString().toIntOrNull()

            if (spinnerResult1 == 3 && spinnerResult2 == 5 || spinnerResult1 == 4 && spinnerResult2 == 4 || spinnerResult1 == 5 && spinnerResult2 == 3) {
                Toast.makeText(this, "Combinació correcte", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("paraula", editText.text.toString())
                intent.putExtra("nombre1", spinnerResult1)
                intent.putExtra("nombre2", spinnerResult2)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Combinació incorrecte!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}