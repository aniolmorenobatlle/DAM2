package dam2.amoreno.uf2_a3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)


        val mecanics = findViewById<Button>(R.id.mecanic)
        val veterinaris = findViewById<Button>(R.id.veterinari)
        val instituts = findViewById<Button>(R.id.institut)
        val restaurants = findViewById<Button>(R.id.restaurants)



    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val palamos = LatLng(41.8625, 3.1261)
        mMap.addMarker(MarkerOptions().position(palamos).title("Palamós"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(palamos, 10f))
    }
}