package com.example.midterm_q3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnCalculator: Button = findViewById(R.id.btnCalculator)
        val btnColorSelector: Button = findViewById(R.id.btnColorSelector)

        btnCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            intent.putExtra("extra_data", "Launching Calculator")
            startActivity(intent)
        }

        btnColorSelector.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data", "Launching Flash Francais")
            startActivity(intent)
        }
    }
}