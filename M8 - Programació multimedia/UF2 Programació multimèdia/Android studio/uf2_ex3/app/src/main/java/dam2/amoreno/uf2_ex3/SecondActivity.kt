package dam2.amoreno.uf2_ex3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dam2.amoreno.uf2_ex3.adapter.CotxeListAdapter
import dam2.amoreno.uf2_ex3.classes.Cotxe
import dam2.amoreno.uf2_ex3.classes.CotxeListClass
import org.w3c.dom.Text

class SecondActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cotxeAdapter: CotxeListAdapter
    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val imatgeCotxe = findViewById<ImageView>(R.id.imatgeCotxe)
        val marcaCotxe = findViewById<TextView>(R.id.marcaCotxe)
        val preuUbi = findViewById<TextView>(R.id.preuUbi)

        val sharedPreferences = getSharedPreferences("cotxes", Context.MODE_PRIVATE)
        val gson = Gson()
        val currentCotxesJson = sharedPreferences.getString("cotxes_list", "[]")

        val cotxeListType = object : TypeToken<MutableList<CotxeListClass>>() {}.type

        val cotxeList = gson.fromJson<MutableList<CotxeListClass>>(currentCotxesJson, cotxeListType)

        val cotxesList = cotxeList.map { cotxe ->
            CotxeListClass(
                cotxe.marca,
                cotxe.preu,
                cotxe.any,
                cotxe.lat,
                cotxe.long
            )
        }.toMutableList()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val cotxesLlista = listOf(
            "audi",
            "bmx",
            "dacia",
            "renault"
        )

        cotxeAdapter = CotxeListAdapter(cotxesList) { selectedCotxe ->
            imatgeCotxe.visibility = View.VISIBLE
            marcaCotxe.visibility = View.VISIBLE
            preuUbi.visibility = View.VISIBLE
            mapView.visibility = View.VISIBLE

            imatgeCotxe.setImageResource(R.drawable.dacia)

            marcaCotxe.text = selectedCotxe.marca

            val cotxe = LatLng(42.0512, 3.1895)

//            val cotxe = LatLng(selectedCotxe.lat.toDouble(), selectedCotxe.long.toDouble())

            mMap.addMarker(MarkerOptions().position(cotxe).title(selectedCotxe.preu.toString() + " euros"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(cotxe))

            mMap.clear()
        }

        recyclerView.adapter = cotxeAdapter
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
}