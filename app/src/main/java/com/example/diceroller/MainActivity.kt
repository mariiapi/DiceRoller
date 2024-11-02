package com.example.diceroller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val diceImages = arrayOf(
        R.drawable.one, R.drawable.two, R.drawable.three,
        R.drawable.four, R.drawable.five, R.drawable.six
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRoll.setOnClickListener {
            rollDice()
        }

    }

    private fun rollDice() {
        with(binding) {
            dice1.setImageResource(diceImages.random())
            dice2.setImageResource(diceImages.random())
            dice3.setImageResource(diceImages.random())
            dice4.setImageResource(diceImages.random())
            dice5.setImageResource(diceImages.random())
        }
    }
}