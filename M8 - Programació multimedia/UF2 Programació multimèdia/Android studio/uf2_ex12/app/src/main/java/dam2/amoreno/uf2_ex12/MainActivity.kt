package dam2.amoreno.uf2_ex12

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val divuit = findViewById<TextView>(R.id.divuit)
        val dinou = findViewById<TextView>(R.id.dinou)
        val vint = findViewById<TextView>(R.id.vint)
        val vintiu = findViewById<TextView>(R.id.vintiu)
        val vintidos = findViewById<TextView>(R.id.vintidos)
        val vintitres = findViewById<TextView>(R.id.vintitres)
        val vintiquatre = findViewById<TextView>(R.id.vintiquatre)
        val vinticinc = findViewById<TextView>(R.id.vinticinc)

        val dniEditText = findViewById<EditText>(R.id.dni)


        val metge = findViewById<TextView>(R.id.metge)
        val infermer = findViewById<TextView>(R.id.infermer)
        val auxiliar = findViewById<TextView>(R.id.auxiliar)
        val administratiu = findViewById<TextView>(R.id.administratiu)

        val guardar = findViewById<Button>(R.id.guardar)
        val area = findViewById<Button>(R.id.area)


        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        divuit.setOnClickListener {
            divuit.setBackgroundColor(Color.BLUE)
            dinou.setBackgroundColor(Color.WHITE)
            vint.setBackgroundColor(Color.WHITE)
            vintiu.setBackgroundColor(Color.WHITE)
            vintidos.setBackgroundColor(Color.WHITE)
            vintitres.setBackgroundColor(Color.WHITE)
            vintiquatre.setBackgroundColor(Color.WHITE)
            vinticinc.setBackgroundColor(Color.WHITE)

            editor.putInt("edat", 18)
        }

        dinou.setOnClickListener {
            divuit.setBackgroundColor(Color.WHITE)
            dinou.setBackgroundColor(Color.BLUE)
            vint.setBackgroundColor(Color.WHITE)
            vintiu.setBackgroundColor(Color.WHITE)
            vintidos.setBackgroundColor(Color.WHITE)
            vintitres.setBackgroundColor(Color.WHITE)
            vintiquatre.setBackgroundColor(Color.WHITE)
            vinticinc.setBackgroundColor(Color.WHITE)

            editor.putInt("edat", 19)
        }

        vint.setOnClickListener {
            divuit.setBackgroundColor(Color.WHITE)
            dinou.setBackgroundColor(Color.WHITE)
            vint.setBackgroundColor(Color.BLUE)
            vintiu.setBackgroundColor(Color.WHITE)
            vintidos.setBackgroundColor(Color.WHITE)
            vintitres.setBackgroundColor(Color.WHITE)
            vintiquatre.setBackgroundColor(Color.WHITE)
            vinticinc.setBackgroundColor(Color.WHITE)

            editor.putInt("edat", 20)
        }

        vintiu.setOnClickListener {
            divuit.setBackgroundColor(Color.WHITE)
            dinou.setBackgroundColor(Color.WHITE)
            vint.setBackgroundColor(Color.WHITE)
            vintiu.setBackgroundColor(Color.BLUE)
            vintidos.setBackgroundColor(Color.WHITE)
            vintitres.setBackgroundColor(Color.WHITE)
            vintiquatre.setBackgroundColor(Color.WHITE)
            vinticinc.setBackgroundColor(Color.WHITE)

            editor.putInt("edat", 21)
        }

        vintidos.setOnClickListener {
            divuit.setBackgroundColor(Color.WHITE)
            dinou.setBackgroundColor(Color.WHITE)
            vint.setBackgroundColor(Color.WHITE)
            vintiu.setBackgroundColor(Color.WHITE)
            vintidos.setBackgroundColor(Color.BLUE)
            vintitres.setBackgroundColor(Color.WHITE)
            vintiquatre.setBackgroundColor(Color.WHITE)
            vinticinc.setBackgroundColor(Color.WHITE)

            editor.putInt("edat", 22)
        }

        vintitres.setOnClickListener {
            divuit.setBackgroundColor(Color.WHITE)
            dinou.setBackgroundColor(Color.WHITE)
            vint.setBackgroundColor(Color.WHITE)
            vintiu.setBackgroundColor(Color.WHITE)
            vintidos.setBackgroundColor(Color.WHITE)
            vintitres.setBackgroundColor(Color.BLUE)
            vintiquatre.setBackgroundColor(Color.WHITE)
            vinticinc.setBackgroundColor(Color.WHITE)

            editor.putInt("edat", 23)
        }

        vintiquatre.setOnClickListener {
            divuit.setBackgroundColor(Color.WHITE)
            dinou.setBackgroundColor(Color.WHITE)
            vint.setBackgroundColor(Color.WHITE)
            vintiu.setBackgroundColor(Color.WHITE)
            vintidos.setBackgroundColor(Color.WHITE)
            vintitres.setBackgroundColor(Color.WHITE)
            vintiquatre.setBackgroundColor(Color.BLUE)
            vinticinc.setBackgroundColor(Color.WHITE)

            editor.putInt("edat", 24)
        }

        vinticinc.setOnClickListener {
            divuit.setBackgroundColor(Color.WHITE)
            dinou.setBackgroundColor(Color.WHITE)
            vint.setBackgroundColor(Color.WHITE)
            vintiu.setBackgroundColor(Color.WHITE)
            vintidos.setBackgroundColor(Color.WHITE)
            vintitres.setBackgroundColor(Color.WHITE)
            vintiquatre.setBackgroundColor(Color.WHITE)
            vinticinc.setBackgroundColor(Color.BLUE)

            editor.putInt("edat", 25)
        }


        metge.setOnClickListener {
            metge.setBackgroundColor(Color.BLUE)
            infermer.setBackgroundColor(Color.WHITE)
            auxiliar.setBackgroundColor(Color.WHITE)
            administratiu.setBackgroundColor(Color.WHITE)

            editor.putString("area", "metge")
        }

        infermer.setOnClickListener {
            metge.setBackgroundColor(Color.WHITE)
            infermer.setBackgroundColor(Color.BLUE)
            auxiliar.setBackgroundColor(Color.WHITE)
            administratiu.setBackgroundColor(Color.WHITE)

            editor.putString("area", "infermer")
        }

        auxiliar.setOnClickListener {
            metge.setBackgroundColor(Color.WHITE)
            infermer.setBackgroundColor(Color.WHITE)
            auxiliar.setBackgroundColor(Color.BLUE)
            administratiu.setBackgroundColor(Color.WHITE)

            editor.putString("area", "auxiliar")
        }

        administratiu.setOnClickListener {
            metge.setBackgroundColor(Color.WHITE)
            infermer.setBackgroundColor(Color.WHITE)
            auxiliar.setBackgroundColor(Color.WHITE)
            administratiu.setBackgroundColor(Color.BLUE)

            editor.putString("area", "administratiu")
        }


        guardar.setOnClickListener {
            editor.putString("dni", dniEditText.text.toString())

            editor.apply()
            Toast.makeText(this, "Guardat", Toast.LENGTH_SHORT).show()
        }

        area.setOnClickListener {
            val intent = Intent(this, AreaActivity::class.java)
            startActivity(intent)
        }

    }
}