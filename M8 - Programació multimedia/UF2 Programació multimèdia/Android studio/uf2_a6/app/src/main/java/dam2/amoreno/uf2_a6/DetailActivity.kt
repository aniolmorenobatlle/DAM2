package dam2.amoreno.uf2_a6

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dam2.amoreno.uf2_a6.classes.Maps
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mapView = findViewById(R.id.mapView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val address = intent.getStringExtra("address")
        val price = intent.getFloatExtra("price", 0.0f)
        val image = intent.getIntExtra("image", 0)

        val addressTextView = findViewById<TextView>(R.id.address)
        val priceTextView = findViewById<TextView>(R.id.price)
        val houseImage = findViewById<ImageView>(R.id.house)

        addressTextView.text = address
        priceTextView.text = DecimalFormat("#,###").format(price) + " €"
        houseImage.setImageResource(image)

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val house = Maps(
            intent.getStringExtra("address").toString(),
            intent.getFloatExtra("lat", 0.0f),
            intent.getFloatExtra("long", 0.0f)
        )

        mMap.addMarker(
            MarkerOptions()
                .position(LatLng(house.latitud.toDouble(), house.longitud.toDouble()))
                .title(intent.getStringExtra("address"))
        )

        val housePosition = LatLng(house.latitud.toDouble(), house.longitud.toDouble())
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(housePosition, 12f))
    }
}
