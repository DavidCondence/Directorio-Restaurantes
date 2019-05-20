package com.proyectofinal.directorio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_ver_contacto.*
import kotlinx.android.synthetic.main.app_bar_main.*

class verContacto :  AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    var lat: String?= null
    var lon: String?= null
    var nombre: String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_contacto)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var bundle = intent.extras
        var imagen: Int = bundle.getInt("imagen")
        nombre = intent.getStringExtra("nombre")
        lat = intent.getStringExtra("lat")
        lon = intent.getStringExtra("lon") 

        supportActionBar!!.title = nombre

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        // Add a marker in Sydney and move the camera
        val sydney = LatLng(lat!!.toDouble(), lon!!.toDouble())
        mMap.addMarker(MarkerOptions().position(sydney).title(nombre))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16.0f))
    }
}
