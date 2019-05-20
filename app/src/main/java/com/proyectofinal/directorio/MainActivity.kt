package com.proyectofinal.directorio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.BaseAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.cotactoview.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var listaContactos = ArrayList<Restaurante>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        var bundle = intent.extras
        if (bundle != null){
            var usuario = bundle.getString("usuario")
            navView.getHeaderView(0).tv_usuario.setText(usuario)
        }

        crearContacto()
        var adaptador = AdaptadorContacto(this,listaContactos)
        listview.adapter = adaptador
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.cerrar_sesion -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                this?.startActivity(intent)

            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    fun crearContacto(){

        listaContactos.add(Restaurante(0, "El jacal taqueria", "27.491708", "-109.973834","Prueba", R.drawable.uno))
        listaContactos.add(Restaurante(1, "Mariscos Obregón", "27.490214", "-109.973340","Prueba", R.drawable.dos))
        listaContactos.add(Restaurante(2, "Subway", "27.494487", "-109.973952","Prueba", R.drawable.tres))
        listaContactos.add(Restaurante(3, "Comida China: Danna's", "27.496267", "-109.973834","Prueba", R.drawable.cuatro))
        listaContactos.add(Restaurante(4, "El antojo", "27.491057", "-109.971163","Prueba", R.drawable.cinco))
        listaContactos.add(Restaurante(5, "Waffle Way", "27.491033", "-109.970036","Prueba", R.drawable.seis))
        listaContactos.add(Restaurante(6, "Bar Salads", "27.491081", "-109.970261","Prueba", R.drawable.siete))
        listaContactos.add(Restaurante(7, "IUME Sushi Express", "27.491029", "-109.969467","Prueba", R.drawable.ocho))
        listaContactos.add(Restaurante(8, "Cafeteria Náinari", "27.492104", "-109.969427","Prueba", R.drawable.gta))
        listaContactos.add(Restaurante(9, "Tortas la Pasadita", "27.490963", "-109.969709","Prueba", R.drawable.nueve))

    }
    private class AdaptadorContacto: BaseAdapter {
        var contexto: Context? = null
        var contactos = ArrayList<Restaurante>()

        constructor(context: Context, contactos: ArrayList<Restaurante>){
            contexto = context
            this.contactos = contactos
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.cotactoview,null)
            var contacto = contactos[position]
            vista.ver_iv_contacto.setImageResource(contacto.imagen)
            vista.ver_tv_nombre.text = contacto.nombre
            vista.setOnClickListener{
                val intent = Intent(contexto, verContacto::class.java)
                intent.putExtra("imagen", contacto.imagen)
                intent.putExtra("nombre", contacto.nombre)
                intent.putExtra("lat", contacto.lat)
                intent.putExtra("lon", contacto.lon)
                intent.putExtra("ciudad", contacto.categoria)
                contexto?.startActivity(intent)

            }
            return vista
        }

        override fun getItem(position: Int): Any {
            return contactos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return contactos.size
        }
    }
}
