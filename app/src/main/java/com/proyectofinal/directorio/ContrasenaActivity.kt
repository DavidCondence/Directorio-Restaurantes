package com.proyectofinal.directorio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contrasena.*

class ContrasenaActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)
        mAuth = FirebaseAuth.getInstance()

        btn_restablecer.setOnClickListener {
            var correo = et_corre_cont.text.toString()
            if (!correo.isNullOrEmpty()){
                mAuth?.sendPasswordResetEmail(correo)
                    ?.addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Se envio un correo a ${correo}",Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Ingresar correo",Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Ingresa un dato",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
