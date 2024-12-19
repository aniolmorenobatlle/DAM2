package dam2.amoreno.uf2_ex2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dam2.amoreno.uf2_ex2.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sharedPreferences = getSharedPreferences("origen", Context.MODE_PRIVATE)
        val latGuardat = sharedPreferences.getFloat("lat", 0000.00f)
        val longGuardat = sharedPreferences.getFloat("long", 0.00f)


        val origen = LatLng(latGuardat.toDouble(), longGuardat.toDouble())
        mMap.addMarker(MarkerOptions().position(origen).title("Origen"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(origen))


        val sharedPreferencesDesti = getSharedPreferences("desti", Context.MODE_PRIVATE)
        val latGuardatDesti = sharedPreferencesDesti.getFloat("lat", 0000.00f)
        val longGuardatDesti = sharedPreferencesDesti.getFloat("long", 0.00f)

        val desti = LatLng(latGuardatDesti.toDouble(), longGuardatDesti.toDouble())
        mMap.addMarker(MarkerOptions().position(desti).title("Desti"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.tornar -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}