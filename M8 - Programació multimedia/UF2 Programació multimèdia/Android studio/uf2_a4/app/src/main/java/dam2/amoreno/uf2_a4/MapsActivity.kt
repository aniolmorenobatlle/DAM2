package dam2.amoreno.uf2_a4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.model.Usuari

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dam2.amoreno.uf2_a4.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        database = FirebaseDatabase.getInstance().getReference("usuari")

        database.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    val usuari = postSnapshot.getValue(Usuari::class.java)
                    val nom = usuari?.nom
                    val poblacio = usuari?.poblacio
                    val latitud = usuari?.position?.get(0)?.toDouble()
                    val longitud = usuari?.position?.get(1)?.toDouble()

                    val posicio = LatLng(latitud!!, longitud!!) // !! perque no sigui nullable
                    mMap.addMarker(MarkerOptions()
                        .position(posicio)
                        .title(nom)
                        .snippet(poblacio)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.user))
                    )
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(posicio))

                    mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }
}