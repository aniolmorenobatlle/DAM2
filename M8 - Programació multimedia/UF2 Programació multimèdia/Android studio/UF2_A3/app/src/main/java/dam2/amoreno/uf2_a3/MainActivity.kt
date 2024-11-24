package dam2.amoreno.uf2_a3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dam2.amoreno.uf2_a3.Classes.Mapes

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)


        val restaurants = findViewById<Button>(R.id.restaurants)
        val mecanics = findViewById<Button>(R.id.mecanic)
        val instituts = findViewById<Button>(R.id.institut)
        val totes = findViewById<Button>(R.id.totes)


        restaurants.setOnClickListener {
            mMap.clear()

            restaurants()
        }

        mecanics.setOnClickListener {
            mMap.clear()

            mecanics()
        }

        instituts.setOnClickListener {
            mMap.clear()

            instituts()
        }

        totes.setOnClickListener {
            mMap.clear()

            restaurants()
            mecanics()
            instituts()
        }
    }

    private fun restaurants() {
        val restaurant = Mapes(1, "Restaurant", "Restaurant Nàutic Palamós", 41.8426, 3.1273)
        mMap.addMarker(MarkerOptions().position(LatLng(restaurant.latitud, restaurant.longitud)).title(restaurant.nom))

        val restaurant2 = Mapes(1, "Restaurant", "Can Paco", 41.8486, 3.1283)
        mMap.addMarker(MarkerOptions().position(LatLng(restaurant2.latitud, restaurant2.longitud)).title(restaurant2.nom))

        val restaurant3 = Mapes(1, "Restaurant", "El Racó", 41.8449, 3.1287)
        mMap.addMarker(MarkerOptions().position(LatLng(restaurant3.latitud, restaurant3.longitud)).title(restaurant3.nom))

        val restaurant4 = Mapes(1, "Restaurant", "Sant Grillat", 41.8452, 3.1287)
        mMap.addMarker(MarkerOptions().position(LatLng(restaurant4.latitud, restaurant4.longitud)).title(restaurant4.nom))
    }

    private fun mecanics() {
        val mecanic = Mapes(2, "Mecànic", "GARATGE ENRIC", 41.8531, 3.1279)
        mMap.addMarker(MarkerOptions().position(LatLng(mecanic.latitud, mecanic.longitud)).title(mecanic.nom))

        val mecanic2 = Mapes(2, "Mecànic", "Mecàniques Palamós 95 SL", 41.8612, 3.1322)
        mMap.addMarker(MarkerOptions().position(LatLng(mecanic2.latitud, mecanic2.longitud)).title(mecanic2.nom))

        val mecanic3 = Mapes(2, "Mecànic", "Garatge Jaume", 41.8511, 3.1224)
        mMap.addMarker(MarkerOptions().position(LatLng(mecanic3.latitud, mecanic3.longitud)).title(mecanic3.nom))

        val mecanic4 = Mapes(2, "Mecànic", "Motos Tot Temps", 41.8531, 3.1285)
        mMap.addMarker(MarkerOptions().position(LatLng(mecanic4.latitud, mecanic4.longitud)).title(mecanic4.nom))
    }

    private fun instituts() {
        val institut = Mapes(3, "Institut", "IES - Institut Públic Palamós", 41.8516, 3.1192)
        mMap.addMarker(MarkerOptions().position(LatLng(institut.latitud, institut.longitud)).title(institut.nom))

        val institut2 = Mapes(3, "Institut", "Institut Baix Empordà", 41.8504, 3.1202)
        mMap.addMarker(MarkerOptions().position(LatLng(institut2.latitud, institut2.longitud)).title(institut2.nom))

        val institut3 = Mapes(3, "Institut", "Institut Públic Empordà", 41.85219249975438, 3.1231255095632413)
        mMap.addMarker(MarkerOptions().position(LatLng(institut3.latitud, institut3.longitud)).title(institut3.nom))

        val institut4 = Mapes(3, "Institut", "Institut Secundaria", 41.85316746412495, 3.1254429379999737)
        mMap.addMarker(MarkerOptions().position(LatLng(institut4.latitud, institut4.longitud)).title(institut4.nom))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val palamos = LatLng(41.8504, 3.1294)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(palamos, 13f))
    }
}