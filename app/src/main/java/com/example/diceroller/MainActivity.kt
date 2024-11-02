package com.example.diceroller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.diceImages.observe(this) { images ->
            with(binding) {
                dice1.setImageResource(images[0])
                dice2.setImageResource(images[1])
                dice3.setImageResource(images[2])
                dice4.setImageResource(images[3])
                dice5.setImageResource(images[4])
            }
        }

        viewModel.isRolling.observe(this) { inProgress ->
            binding.btnRoll.isEnabled = !inProgress
            binding.btnStop.isEnabled = inProgress
        }

        binding.btnRoll.setOnClickListener {
            viewModel.startRolling()
        }

        binding.btnStop.setOnClickListener {
            Toast.makeText(this@MainActivity, "Shuffle them a little more...", Toast.LENGTH_SHORT).show()
            viewModel.stopRolling()
        }
    }

}
