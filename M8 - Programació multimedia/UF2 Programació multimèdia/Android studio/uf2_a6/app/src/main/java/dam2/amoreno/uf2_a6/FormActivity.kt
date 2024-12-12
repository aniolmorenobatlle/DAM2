package dam2.amoreno.uf2_a6

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        val add = findViewById<ImageView>(R.id.add)
        val see = findViewById<ImageView>(R.id.see)
        val home = findViewById<ImageView>(R.id.home)


        add.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

//        see.setOnClickListener {
//            val intent = Intent(this, InvertaryActivity::class.java)
//            startActivity(intent)
//        }

        home.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}