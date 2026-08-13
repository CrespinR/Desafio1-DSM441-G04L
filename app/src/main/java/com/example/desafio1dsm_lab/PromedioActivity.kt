package com.example.desafio1dsm_lab


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    private lateinit var etNombreEstudiante: EditText
    private lateinit var etNota1: EditText
    private lateinit var etNota2: EditText
    private lateinit var etNota3: EditText
    private lateinit var etNota4: EditText
    private lateinit var etNota5: EditText

    private lateinit var tvPromedioFinal: TextView
    private lateinit var tvEstadoEstudiante: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        etNombreEstudiante = findViewById(R.id.etNombreEstudiante)
        etNota1 = findViewById(R.id.etNota1)
        etNota2 = findViewById(R.id.etNota2)
        etNota3 = findViewById(R.id.etNota3)
        etNota4 = findViewById(R.id.etNota4)
        etNota5 = findViewById(R.id.etNota5)

        tvPromedioFinal = findViewById(R.id.tvPromedioFinal)
        tvEstadoEstudiante = findViewById(R.id.tvEstadoEstudiante)

        val btnCalcularPromedio =
            findViewById<Button>(R.id.btnCalcularPromedio)

        val btnVolverMenuPromedio =
            findViewById<Button>(R.id.btnVolverMenuPromedio)

        btnCalcularPromedio.setOnClickListener {
            calcularPromedio()
        }

        btnVolverMenuPromedio.setOnClickListener {
            finish()
        }
    }

    private fun calcularPromedio() {

        if (etNombreEstudiante.text.toString().trim().isEmpty()) {
            etNombreEstudiante.error =
                getString(R.string.campo_obligatorio)
            return
        }

        val notas = listOf(
            etNota1,
            etNota2,
            etNota3,
            etNota4,
            etNota5
        )

        val valores = mutableListOf<Double>()

        for (campo in notas) {

            if (campo.text.toString().trim().isEmpty()) {
                campo.error = getString(R.string.campo_obligatorio)
                return
            }

            val nota = campo.text.toString().toDoubleOrNull()

            if (nota == null || nota !in 0.0..10.0) {
                campo.error = getString(R.string.nota_invalida)
                return
            }

            valores.add(nota)
        }


        val promedio = calcularPromedioPonderado(valores)

        val formato = DecimalFormat("0.00")
        val promedioFormateado = formato.format(promedio)

        tvPromedioFinal.text =
            getString(
                R.string.promedio_final
            ) + ": " + promedioFormateado

        if (promedio >= 6.0) {
            tvEstadoEstudiante.text =
                getString(R.string.estado_estudiante) +
                        ": " +
                        getString(R.string.aprobado)
        } else {
            tvEstadoEstudiante.text =
                getString(R.string.estado_estudiante) +
                        ": " +
                        getString(R.string.reprobado)
        }
    }

    private fun calcularPromedioPonderado(
        notas: List<Double>
    ): Double {

        /*
         * Las ponderaciones deben reemplazarse
         * cuando tengamos la tabla del profesor.
         */

        return notas.average()
    }
}