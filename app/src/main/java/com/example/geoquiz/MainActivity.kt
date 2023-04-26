package com.example.geoquiz
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.geoquiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    var numRespuestasCorrectas = 0
    var numPreguntasContestadas = 0

    val preguntas= listOf<Pregunta>(Pregunta(R.string.pregunta_australia,true),
        Pregunta(R.string.pregunta_oceanos,true),
        Pregunta(R.string.pregunta_medio_oriente,false),
        Pregunta(R.string.pregunta_africa,false),
        Pregunta(R.string.pregunta_america,true),
        Pregunta(R.string.pregunta_asia,true))
    var indiceActual:Int=0







    private lateinit var layout: ConstraintLayout
    private var isDarkModeOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pregunta:Int=preguntas[indiceActual].idRpta
        binding.tvPregunta.text=getString(pregunta)


        // Asignar valor a layout
        layout = binding.layout

        // Set initial background color
        setLayoutBackground()

        // Set up dark mode button click listener
        binding.darkModeButton.setOnClickListener {
            isDarkModeOn = !isDarkModeOn // Toggle dark mode boolean
            setLayoutBackground() // Update layout background
            setDarkModeButtonText() // Update dark mode button text
        }

        binding.trueButton.setOnClickListener{
            verificarRespuesta(true)
        }

        binding.falseButton.setOnClickListener{
            verificarRespuesta(false)
        }

        binding.botonSiguiente.setOnClickListener{
            if (indiceActual < preguntas.size - 1) { // Verificar si hay más preguntas
                indiceActual+=1
                pregunta=preguntas[indiceActual].idRpta
                binding.tvPregunta.text=getString(pregunta)
                binding.trueButton.isEnabled = false
            } else {
                val porcentaje = numRespuestasCorrectas * 100 / preguntas.size
                Toast.makeText(this, "¡Has llegado al final!", Toast.LENGTH_SHORT).show()
                val mensaje = "¡Has contestado todas las preguntas!\nTu porcentaje de aciertos es: $porcentaje%"
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            }
        }
        binding.botonAnterior.setOnClickListener {
            if (indiceActual > 0) { // Verificar si hay preguntas anteriores
                indiceActual -= 1
                pregunta = preguntas[indiceActual].idRpta
                binding.tvPregunta.text = getString(pregunta)
                binding.trueButton.isEnabled = false
            } else {
                Toast.makeText(this, "¡Esta es la primera pregunta!", Toast.LENGTH_SHORT).show()
            }
        }
        fun verificarRespuesta(rptaUsuario:Boolean){
            var rptaCorrecta=preguntas[indiceActual].rptaVerdadera
            var msgRpta:Int=0
            if (rptaUsuario==rptaCorrecta) {
                msgRpta = R.string.correct_toast
                numRespuestasCorrectas++
            } else {
                msgRpta = R.string.incorrect_toast
            }
            Toast.makeText(this,msgRpta,Toast.LENGTH_SHORT).show()
            numPreguntasContestadas++
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() fue llamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() fue llamado")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause) fue llamado")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop() fue llamado")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy() fue llamado")
    }








    // Set the background color of the layout based on the current dark mode state
    private fun setLayoutBackground() {
        if (isDarkModeOn) {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_background))
            binding.tvPregunta.setTextColor(ContextCompat.getColor(this, R.color.dark_text))
        } else {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_background))
            binding.tvPregunta.setTextColor(ContextCompat.getColor(this, R.color.light_text))
        }
    }
    private fun verificarRespuesta(rptaUsuario:Boolean){
        var rptaCorrecta=preguntas[indiceActual].rptaVerdadera
        var msgRpta:Int=0
        msgRpta=if (rptaUsuario==rptaCorrecta)R.string.correct_toast else R.string.incorrect_toast
        Toast.makeText(this,msgRpta,Toast.LENGTH_SHORT).show()
    }



    // Set the text of the dark mode button based on the current dark mode state
    private fun setDarkModeButtonText() {
        if (isDarkModeOn) {
            binding.darkModeButton.text = getString(R.string.light_mode_button_text)
        } else {
            binding.darkModeButton.text = getString(R.string.dark_mode_button_text)
        }
    }
}
