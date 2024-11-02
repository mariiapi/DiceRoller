package com.example.diceroller

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diceroller.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val diceImages = arrayOf(
        R.drawable.one, R.drawable.two, R.drawable.three,
        R.drawable.four, R.drawable.five, R.drawable.six
    )

    private var inProgress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStop.isEnabled = false
        binding.btnRoll.setOnClickListener {
            rollDiceUntilStop()
        }
        binding.btnStop.setOnClickListener{
            inProgress = false
        }


    }

    private fun rollDiceUntilStop(){
        Log.d(TAG, "rollDiceUntilStop: Start rolling.")
        inProgress = true
        binding.btnStop.isEnabled = true
        while (true){
            Log.d(TAG, "rollDiceUntilStop: roll.")
            rollDice()
            if (!inProgress){
                Log.d(TAG, "rollDiceUntilStop: Stop rolling.")
                binding.btnStop.isEnabled = false
                return
            }
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

    companion object{
        const val TAG = "XXXX"
    }
}