package dam2.amoreno.uf2_a3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
        val restaurant = Mapes(1, "Restaurant", "Restaurant Nàutic Palamós", 41.8426, 3.1275)
        val marker1 = mMap.addMarker(MarkerOptions()
            .position(LatLng(restaurant.latitud, restaurant.longitud))
            .title(restaurant.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant)))

        val restaurant2 = Mapes(1, "Restaurant", "Can Paco", 41.8486, 3.1286)
        val marker2 = mMap.addMarker(MarkerOptions()
            .position(LatLng(restaurant2.latitud, restaurant2.longitud))
            .title(restaurant2.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant)))

        val restaurant3 = Mapes(1, "Restaurant", "El Racó", 41.8449, 3.1285)
        val marker3 = mMap.addMarker(MarkerOptions()
            .position(LatLng(restaurant3.latitud, restaurant3.longitud))
            .title(restaurant3.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant)))

        val restaurant4 = Mapes(1, "Restaurant", "L'arbre de Juliette", 41.8453, 3.1285)
        val marker4 = mMap.addMarker(MarkerOptions()
            .position(LatLng(restaurant4.latitud, restaurant4.longitud))
            .title(restaurant4.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant)))


        mMap.setOnMarkerClickListener { marker ->
            when (marker) {
                marker1 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(restaurant.tipus)
                        .setMessage(restaurant.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker2 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(restaurant2.tipus)
                        .setMessage(restaurant2.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker3 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(restaurant3.tipus)
                        .setMessage(restaurant3.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker4 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(restaurant4.tipus)
                        .setMessage(restaurant4.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }
            }
            true
        }
    }

    private fun mecanics() {
        val mecanic = Mapes(2, "Mecànic", "GARATGE ENRIC", 41.8531, 3.1279)
        val marker5 = mMap.addMarker(MarkerOptions()
            .position(LatLng(mecanic.latitud, mecanic.longitud))
            .title(mecanic.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mechanics)))

        val mecanic2 = Mapes(2, "Mecànic", "Mecàniques Palamós 95 SL", 41.8612, 3.1322)
        val marker6 = mMap.addMarker(MarkerOptions()
            .position(LatLng(mecanic2.latitud, mecanic2.longitud))
            .title(mecanic2.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mechanics)))

        val mecanic3 = Mapes(2, "Mecànic", "Garatge Jaume", 41.8511, 3.1224)
        val marker7 = mMap.addMarker(MarkerOptions()
            .position(LatLng(mecanic3.latitud, mecanic3.longitud))
            .title(mecanic3.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mechanics)))

        val mecanic4 = Mapes(2, "Mecànic", "Motos Tot Temps", 41.8531, 3.1285)
        val marker8 = mMap.addMarker(MarkerOptions()
            .position(LatLng(mecanic4.latitud, mecanic4.longitud))
            .title(mecanic4.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mechanics)))

        mMap.setOnMarkerClickListener { marker ->
            when (marker) {
                marker5 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(mecanic.tipus)
                        .setMessage(mecanic.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker6 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(mecanic2.tipus)
                        .setMessage(mecanic2.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker7 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(mecanic3.tipus)
                        .setMessage(mecanic3.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker8 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(mecanic4.tipus)
                        .setMessage(mecanic4.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }
            }
            true
        }
    }

    private fun instituts() {
        val institut = Mapes(3, "Institut", "IES - Institut Públic Palamós", 41.8516, 3.1192)
        val marker9 = mMap.addMarker(MarkerOptions()
            .position(LatLng(institut.latitud, institut.longitud))
            .title(institut.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.school)))

        val institut2 = Mapes(3, "Institut", "Institut Baix Empordà", 41.8504, 3.1202)
        val marker10 = mMap.addMarker(MarkerOptions()
            .position(LatLng(institut2.latitud, institut2.longitud))
            .title(institut2.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.school)))

        val institut3 = Mapes(3, "Institut", "Institut Públic Empordà", 41.85219249975438, 3.1231255095632413)
        val marker11 = mMap.addMarker(MarkerOptions()
            .position(LatLng(institut3.latitud, institut3.longitud))
            .title(institut3.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.school)))

        val institut4 = Mapes(3, "Institut", "Institut Secundaria", 41.85316746412495, 3.1254429379999737)
        val marker12 = mMap.addMarker(MarkerOptions()
            .position(LatLng(institut4.latitud, institut4.longitud))
            .title(institut4.nom)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.school)))

        mMap.setOnMarkerClickListener { marker ->
            when (marker) {
                marker9 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(institut.tipus)
                        .setMessage(institut.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker10 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(institut2.tipus)
                        .setMessage(institut2.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker11 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(institut3.tipus)
                        .setMessage(institut3.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }

                marker12 -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(institut4.tipus)
                        .setMessage(institut4.nom)
                        .setPositiveButton("Tancar") { dialog, _ -> dialog.dismiss() }
                    builder.create().show()
                }
            }
            true
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val palamos = LatLng(41.8504, 3.1294)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(palamos, 13f))
    }
}