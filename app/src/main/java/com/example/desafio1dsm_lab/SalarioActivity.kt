package com.example.desafio1dsm_lab

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager

class SalarioActivity : AppCompatActivity() {

    private lateinit var etNombreEmpleado: EditText
    private lateinit var etSalarioBase: EditText

    private lateinit var tvSalarioBruto: TextView
    private lateinit var tvRenta: TextView
    private lateinit var tvAfp: TextView
    private lateinit var tvIsss: TextView
    private lateinit var tvSalarioNeto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        etNombreEmpleado = findViewById(R.id.etNombreEmpleado)
        etSalarioBase = findViewById(R.id.etSalarioBase)

        tvSalarioBruto = findViewById(R.id.tvSalarioBruto)
        tvRenta = findViewById(R.id.tvRenta)
        tvAfp = findViewById(R.id.tvAfp)
        tvIsss = findViewById(R.id.tvIsss)
        tvSalarioNeto = findViewById(R.id.tvSalarioNeto)

        val btnCalcularSalario =
            findViewById<Button>(R.id.btnCalcularSalario)

        val btnVolverMenuSalario =
            findViewById<Button>(R.id.btnVolverMenuSalario)

        btnCalcularSalario.setOnClickListener {
            calcularSalario()
        }

        btnVolverMenuSalario.setOnClickListener {
            finish()
        }
    }

    private fun calcularSalario() {

        if (etNombreEmpleado.text.toString().trim().isEmpty()) {
            etNombreEmpleado.error =
                getString(R.string.campo_obligatorio)
            return
        }

        val salario = etSalarioBase.text.toString().toDoubleOrNull()

        if (salario == null || salario <= 0) {

            etSalarioBase.error =
                getString(R.string.salario_invalido)

            vibrarDispositivo()

            return
        }

        val afp = salario * 0.0725
        val isss = salario * 0.03

        val renta = calcularRenta(salario)

        val salarioNeto = salario - afp - isss - renta

        tvSalarioBruto.text =
            getString(R.string.salario_bruto) +
                    ": $" +
                    String.format("%.2f", salario)

        tvAfp.text =
            getString(R.string.afp) +
                    ": $" +
                    String.format("%.2f", afp)

        tvIsss.text =
            getString(R.string.isss) +
                    ": $" +
                    String.format("%.2f", isss)

        tvRenta.text =
            getString(R.string.renta) +
                    ": $" +
                    String.format("%.2f", renta)

        tvSalarioNeto.text =
            getString(R.string.salario_neto) +
                    ": $" +
                    String.format("%.2f", salarioNeto)
    }

    private fun calcularRenta(salario: Double): Double {

        return when {
            // I TRAMO
            salario <= 472.00 -> {
                0.0
            }

            // II TRAMO
            salario <= 895.24 -> {
                ((salario - 472.00) * 0.10) + 17.67
            }

            // III TRAMO
            salario <= 2038.10 -> {
                ((salario - 895.24) * 0.20) + 60.00
            }

            // IV TRAMO
            else -> {
                ((salario - 2038.10) * 0.30) + 288.57
            }
        }
    }


    private fun vibrarDispositivo() {

        val vibrator = if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.S
        ) {

            val vibratorManager =
                getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
                        as VibratorManager

            vibratorManager.defaultVibrator

        } else {

            @Suppress("DEPRECATION")
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        if (!vibrator.hasVibrator()) {
            return
        }

        if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.O
        ) {

                VibrationEffect.createOneShot(
                    300,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )


        } else {

        }
    }
}