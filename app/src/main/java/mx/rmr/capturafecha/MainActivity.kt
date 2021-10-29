package mx.rmr.capturafecha

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.rmr.capturafecha.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

/*
Ejemplo de captura de Fecha y Hora con Diálogos de Android
Autor: Roberto Martínez Román

HAY UN GRADIENTE EN res/drawable QUE SE USA COMO FONDO DE LA VISTA

 */
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarCapturaFechaHora()
    }

    private fun configurarCapturaFechaHora() {
        // Configuramos la captura de la fecha
        val etFecha = binding.etFecha
        val calendario = Calendar.getInstance()
        // Valores iniciales (hora/fecha del sistema)
        val fechaFormato = String.format(
            "%d-%02d-%d",
            calendario.get(Calendar.DAY_OF_MONTH),
            calendario.get(Calendar.MONTH) + 1,
            calendario.get(Calendar.YEAR)
        )
        etFecha.setText(fechaFormato)   // Muestra la fecha de hoy

        // Listener para atender el click sobre el campo fecha
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Lee la  fecha seleccionada
                calendario.set(Calendar.YEAR, year)
                calendario.set(Calendar.MONTH, monthOfYear)
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                // Pone la fecha seleccionada en el campo de texto
                val formato = "d-MM-yyyy"
                val sdf = SimpleDateFormat(formato, Locale.ENGLISH)
                etFecha.setText(sdf.format(calendario.time))
            }

        // Asigna el listener al campo
        etFecha.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener,
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Configuramos la captura de hora
        val etHora = binding.etHora
        val horaFormato = String.format("%d:%02d", calendario.get(Calendar.HOUR_OF_DAY),
            calendario.get(Calendar.MINUTE))
        etHora.setText(horaFormato)     // Muestra la hora actual

        // Listener para atender el click sobre el campo hora
        val horaListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendario.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendario.set(Calendar.MINUTE, minute)
            // Pone la hora seleccionada en el campo de texto
            val formato = "H:mm"
            val stf = SimpleDateFormat(formato, Locale.ENGLISH)
            etHora.setText(stf.format(calendario.time))
        }

        // Asigna el listener al campo
        etHora.setOnClickListener {
            TimePickerDialog(this, horaListener,
                calendario.get(Calendar.HOUR_OF_DAY),
                calendario.get(Calendar.MINUTE), true
            ).show()
        }
    }
}