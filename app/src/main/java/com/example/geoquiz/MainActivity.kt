package com.example.geoquiz
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.geoquiz.databinding.ActivityMainBinding
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var layout: ConstraintLayout
    private var isDarkModeOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }

        binding.falseButton.setOnClickListener{
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }
    }

    // Set the background color of the layout based on the current dark mode state
    private fun setLayoutBackground() {
        if (isDarkModeOn) {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_background))
            binding.textView.setTextColor(ContextCompat.getColor(this, R.color.dark_text))
        } else {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_background))
            binding.textView.setTextColor(ContextCompat.getColor(this, R.color.light_text))
        }
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
