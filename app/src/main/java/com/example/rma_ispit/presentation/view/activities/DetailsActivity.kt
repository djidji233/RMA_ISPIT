package com.example.rma_ispit.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.rma_ispit.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mesto_datumTv.text = intent.getStringExtra("name") + ", " + intent.getStringExtra("date")
        max_dnevnaTv.text = "Maksimalna dnevna temperatura: " + String.format("%.1f C", intent.getFloatExtra("maxtemp",0.0f))
        min_dnevnaTv.text = "Minimalna dnevna temperatura: " + String.format("%.1f C", intent.getFloatExtra("mintemp",0.0f))
        brzina_vetraTv.text = "Brzina vetra: " + String.format("%.1f mph", intent.getFloatExtra("wind",0.0f))
        koef_uvTv.text = "Koeficijent UV zracenja: " + String.format("%.1f", intent.getFloatExtra("uv",0.0f))

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
        
        val lat = intent.getFloatExtra("lat", 0.0f).toDouble()
        val lon = intent.getFloatExtra("lon", 0.0f).toDouble()
        val city = LatLng(lat, lon)
        mMap.addMarker(MarkerOptions().position(city).title("City"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city,8.0f))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}