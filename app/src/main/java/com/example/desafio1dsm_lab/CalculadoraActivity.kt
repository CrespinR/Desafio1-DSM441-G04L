package com.example.desafio1dsm_lab

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var etPrimerNumero: EditText
    private lateinit var etSegundoNumero: EditText
    private lateinit var tvResultadoCalculadora: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        etPrimerNumero = findViewById(R.id.etPrimerNumero)
        etSegundoNumero = findViewById(R.id.etSegundoNumero)

        tvResultadoCalculadora =
            findViewById(R.id.tvResultadoCalculadora)

        val btnSuma =
            findViewById<Button>(R.id.btnSuma)

        val btnResta =
            findViewById<Button>(R.id.btnResta)

        val btnMultiplicacion =
            findViewById<Button>(R.id.btnMultiplicacion)

        val btnDivision =
            findViewById<Button>(R.id.btnDivision)

        val btnExponente =
            findViewById<Button>(R.id.btnExponente)

        val btnRaizCuadrada =
            findViewById<Button>(R.id.btnRaizCuadrada)

        val btnGuardarHistorial =
            findViewById<Button>(R.id.btnGuardarHistorial)

        val btnVolverMenuCalculadora =
            findViewById<Button>(R.id.btnVolverMenuCalculadora)

        btnSuma.setOnClickListener {
            realizarOperacion("suma")
        }

        btnResta.setOnClickListener {
            realizarOperacion("resta")
        }

        btnMultiplicacion.setOnClickListener {
            realizarOperacion("multiplicacion")
        }

        btnDivision.setOnClickListener {
            realizarOperacion("division")
        }

        btnExponente.setOnClickListener {
            realizarOperacion("exponente")
        }

        btnRaizCuadrada.setOnClickListener {
            calcularRaiz()
        }

        btnGuardarHistorial.setOnClickListener {
            guardarHistorial()
        }

        btnVolverMenuCalculadora.setOnClickListener {
            finish()
        }
    }


}