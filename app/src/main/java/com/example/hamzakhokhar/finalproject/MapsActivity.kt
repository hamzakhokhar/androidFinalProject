package com.example.hamzakhokhar.finalproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var logOffButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        //setSupportActionBar(toolbar)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        logOffButton = findViewById(R.id.logOffButton)
        logOffButton.setOnClickListener {
            Toast.makeText(this, "Logging off...", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }


    }
    fun openCreateEvent(view: View){
        val intent = Intent(this,CreateEvent::class.java)
        startActivity(intent)
             // Toast.makeText(this, "new event created", Toast.LENGTH_SHORT).show()
    }
    fun openActivityList(view: View){
        val intent = Intent(this,ActivityList::class.java)
        startActivity(intent)
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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.newEvent -> {

              val intent = Intent(this,CreateEvent::class.java)
                startActivity(intent)
//               Toast.makeText(this, "new event created", Toast.LENGTH_SHORT).show()
               return super.onOptionsItemSelected(item)
            }

            R.id.listEvent -> {
                val intent = Intent(this,ActivityList::class.java)
                startActivity(intent)
               // Toast.makeText(this, "list of events", Toast.LENGTH_SHORT).show()
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val denver = LatLng(39.742043, -104.991531)
        mMap.addMarker(MarkerOptions().position(denver).title("Marker in denver"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(denver))
    }
}
