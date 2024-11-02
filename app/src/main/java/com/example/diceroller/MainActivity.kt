package com.example.diceroller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val diceImages = arrayOf(
        R.drawable.one, R.drawable.two, R.drawable.three,
        R.drawable.four, R.drawable.five, R.drawable.six
    )

    private var inProgress = false
    private var rollJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStop.isEnabled = false

        binding.btnRoll.setOnClickListener {
            startRolling()
        }
        binding.btnStop.setOnClickListener{
            stopRolling()
        }


    }

    private fun startRolling(){
        Log.d(TAG, "rollDiceUntilStop: Start rolling.")
        inProgress = true
        binding.btnRoll.isEnabled = false
        binding.btnStop.isEnabled = true

        rollJob = CoroutineScope(Dispatchers.Main).launch {
            rollDiceUntilStop()
        }
    }

    private fun stopRolling() {
        Log.d(TAG, "stopRolling: Stop rolling.")
        inProgress = false
        binding.btnStop.isEnabled = false
        binding.btnRoll.isEnabled = true
        rollJob?.cancel()
    }

    private suspend fun rollDiceUntilStop() {
        while (inProgress) {
            rollDice()
            Log.d(TAG, "rollDiceUntilStop: roll.")
            delay(500)
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