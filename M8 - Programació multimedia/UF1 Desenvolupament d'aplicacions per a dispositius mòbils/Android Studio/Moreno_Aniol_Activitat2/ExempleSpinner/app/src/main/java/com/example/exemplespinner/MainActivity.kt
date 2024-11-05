package com.example.exemplespinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonComanda : Button = findViewById(R.id.buttonComanda)


        val textViewProductes : TextView = findViewById(R.id.textViewProductes)
        val textViewPreu : TextView = findViewById(R.id.textViewPrice)
        val textViewTotalPrice : TextView = findViewById(R.id.textViewTotalPrice)

        val plats1 : Spinner = findViewById(R.id.spinnerItems1)
        val textViewResultat1 : TextView = findViewById(R.id.textViewResultat1)

        val plats2 : Spinner = findViewById(R.id.spinnerItems2)
        val textViewResultat2 : TextView = findViewById(R.id.textViewResultat2)

        val plats3 : Spinner = findViewById(R.id.spinnerItems3)
        val textViewResultat3 : TextView = findViewById(R.id.textViewResultat3)


        buttonComanda.setOnClickListener {
            textViewProductes.text = getString(R.string.productes)
            textViewPreu.text = getString(R.string.price)
            buttonComanda.text = getString(R.string.comanda_realitzada)

            val selectedPlat1 = plats1.selectedItem.toString().split(" - ")[0]
            val pricePlat1 = plats1.selectedItem.toString().split(" - ")[1].replace("€", "").toInt()

            val selectedPlat2 = plats2.selectedItem.toString().split(" - ")[0]
            val pricePlat2 = plats2.selectedItem.toString().split(" - ")[1].replace("€", "").toInt()

            val selectedPlat3 = plats3.selectedItem.toString().split(" - ")[0]
            val pricePlat3 = plats3.selectedItem.toString().split(" - ")[1].replace("€", "").toInt()

            // Mostrar només el nom dels plats seleccionats
            textViewResultat1.text = selectedPlat1
            textViewResultat2.text = selectedPlat2
            textViewResultat3.text = selectedPlat3

            // Calcular el preu total i mostrar-lo
            val totalPrice = pricePlat1 + pricePlat2 + pricePlat3
            textViewTotalPrice.text = "${totalPrice}€"
        }

        // fun = function
        fun enableButton() {
            val isPlat1Enabled = if (plats1.selectedItemPosition == 0) false else true
            val isPlat2Enabled = if (plats2.selectedItemPosition == 0) false else true
            val isPlat3Enabled = if (plats3.selectedItemPosition == 0) false else true


            if (isPlat1Enabled && isPlat2Enabled && isPlat3Enabled) {
                buttonComanda.isEnabled = true
            } else {
                buttonComanda.isEnabled = false
            }
        }


        plats1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                enableButton()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        plats2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                enableButton()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        plats3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                enableButton()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }
}
