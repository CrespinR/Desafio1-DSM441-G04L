package com.example.desafio1dsm_lab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPromedioEstudiante =
            findViewById<Button>(R.id.btnPromedioEstudiante)

        val btnDescuentosSalario =
            findViewById<Button>(R.id.btnDescuentosSalario)

        val btnCalculadoraBasica =
            findViewById<Button>(R.id.btnCalculadoraBasica)

        btnPromedioEstudiante.setOnClickListener {
            val intent = Intent(this, PromedioActivity::class.java)
            startActivity(intent)
        }

        btnDescuentosSalario.setOnClickListener {
            val intent = Intent(this, SalarioActivity::class.java)
            startActivity(intent)
        }

        btnCalculadoraBasica.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
    }

}