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
    private fun realizarOperacion(operacion: String) {

        val numero1 =
            etPrimerNumero.text.toString().toDoubleOrNull()

        val numero2 =
            etSegundoNumero.text.toString().toDoubleOrNull()

        if (numero1 == null) {
            etPrimerNumero.error =
                getString(R.string.campo_obligatorio)
            return
        }

        if (numero2 == null) {
            etSegundoNumero.error =
                getString(R.string.campo_obligatorio)
            return
        }

        val resultado: Double

        when (operacion) {

            "suma" -> {
                resultado = numero1 + numero2
            }

            "resta" -> {
                resultado = numero1 - numero2
            }

            "multiplicacion" -> {
                resultado = numero1 * numero2
            }

            "division" -> {

                if (numero2 == 0.0) {

                    Toast.makeText(
                        this,
                        getString(R.string.division_cero),
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                resultado = numero1 / numero2
            }

            "exponente" -> {
                resultado = numero1.pow(numero2)
            }

            else -> {
                return
            }
        }

        tvResultadoCalculadora.text =
            getString(R.string.resultado_calculadora) +
                    ": " +
                    String.format("%.2f", resultado)
    }

    private fun calcularRaiz() {

        val numero =
            etPrimerNumero.text.toString().toDoubleOrNull()

        if (numero == null) {
            etPrimerNumero.error =
                getString(R.string.campo_obligatorio)
            return
        }

        if (numero < 0) {
            etPrimerNumero.error =
                "No se puede calcular la raíz de un número negativo"
            return
        }

        val resultado = sqrt(numero)

        tvResultadoCalculadora.text =
            getString(R.string.resultado_calculadora) +
                    ": " +
                    String.format("%.2f", resultado)
    }

    private fun guardarHistorial() {

        val resultado =
            tvResultadoCalculadora.text.toString()

        try {

            openFileOutput(
                "historial.txt",
                MODE_APPEND
            ).use { archivo ->

                archivo.write(
                    "$resultado\n".toByteArray()
                )
            }

            Toast.makeText(
                this,
                "Operación guardada",
                Toast.LENGTH_SHORT
            ).show()

        } catch (e: Exception) {

            Toast.makeText(
                this,
                "Error al guardar",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}