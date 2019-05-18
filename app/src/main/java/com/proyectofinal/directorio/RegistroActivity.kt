package com.proyectofinal.directorio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*



class RegistroActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        mAuth = FirebaseAuth.getInstance();
        btn_registrar.setOnClickListener {
            registrar()
        }
    }
    private fun registrar(){
        var correo = et_correo_reg.text.toString()
        var contra1 = et_contra_reg.text.toString()
        var contra2 = et_contra2_reg.text.toString()


        if (!correo.isNullOrEmpty() && !contra1.isNullOrEmpty() && !contra2.isNullOrEmpty()){
            if (contra1 == contra2){
                registrarFireBase(correo, contra1)
            } else {
                Toast.makeText(this,"Contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"Ingrear datos",Toast.LENGTH_SHORT).show()
        }
    }
    private fun registrarFireBase(correo: String,contra: String){
        mAuth?.createUserWithEmailAndPassword(correo, contra)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth?.getCurrentUser()
                    Toast.makeText(
                        this, "${user?.email} ha sido registrado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        this, "Error al registrarse",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
