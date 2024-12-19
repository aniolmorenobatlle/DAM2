package dam2.amoreno.uf2_ex1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dni = findViewById<EditText>(R.id.dni)
        val guardar = findViewById<Button>(R.id.guardar)
        val area = findViewById<Button>(R.id.area)

        val divuit = findViewById<TextView>(R.id.divuit)
        val dinou = findViewById<TextView>(R.id.dinou)
        val vint = findViewById<TextView>(R.id.vint)
        val vintiun = findViewById<TextView>(R.id.vintiu)
        val vintidos = findViewById<TextView>(R.id.vintidos)
        val vintitres = findViewById<TextView>(R.id.vintitres)
        val vintiquatre = findViewById<TextView>(R.id.vintiquatre)
        val vinticinc = findViewById<TextView>(R.id.vinticinc)

        divuit.setOnClickListener {
            divuit.setBackgroundColor(Color.BLUE)
        }
        //dinou.setOnClickListener { canviarColorFons() }
        //vint.setOnClickListener { canviarColorFons() }
//        vintiun.setOnClickListener { canviarColorFons() }
//        vintidos.setOnClickListener { canviarColorFons() }
//        vintitres.setOnClickListener { canviarColorFons() }
//        vintiquatre.setOnClickListener { canviarColorFons() }
//        vinticinc.setOnClickListener { canviarColorFons() }

        guardar.setOnClickListener {
            val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("rol", "hola")
            editor.putString("dni", "745874584A")
            editor.putInt("edat", 123)
            editor.apply()
        }

        area.setOnClickListener {
            //val intent = Intent(this, SecondActivity::class.java)
            //startActivity(intent)
        }
    }

    private fun canviarColorFons(String edat) {

    }
}