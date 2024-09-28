package com.example.dividircuenta

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener referencias a los componentes de la interfaz
        val totalAmountInput = findViewById<EditText>(R.id.totalAmount)
        val numParticipantsInput = findViewById<EditText>(R.id.numParticipants)
        val switchTip = findViewById<Switch>(R.id.switchTip)
        val sliderTip = findViewById<Slider>(R.id.sliderTip)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val resultText = findViewById<TextView>(R.id.resultText)

        // Habilitar o deshabilitar el Slider basado en el estado del Switch
        switchTip.setOnCheckedChangeListener { _, isChecked ->
            sliderTip.isEnabled = isChecked
        }

        // Calcular monto por persona al hacer clic en el botón
        buttonCalculate.setOnClickListener {
            // Obtener y validar la cantidad total
            val totalAmount = totalAmountInput.text.toString().toDoubleOrNull()
            if (totalAmount == null || totalAmount <= 0) {
                Toast.makeText(this, "Ingrese un monto total válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener y validar el número de participantes
            val numParticipants = numParticipantsInput.text.toString().toIntOrNull()
            if (numParticipants == null || numParticipants <= 0) {
                Toast.makeText(this, "Ingrese un número de participantes válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener el porcentaje de propina desde el Slider
            val tipPercentage = if (switchTip.isChecked) sliderTip.value.toInt() * 5 else 0

            // Calcular el monto total con propina
            val totalWithTip = totalAmount * (1 + tipPercentage / 100.0)

            // Calcular el monto por persona
            val amountPerPerson = totalWithTip / numParticipants

            // Mostrar el resultado en el TextView
            resultText.text = "Monto por persona: %.2f".format(amountPerPerson)
        }
    }
}
