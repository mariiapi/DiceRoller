package com.example.diceroller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val diceImagesArray = arrayOf(
        R.drawable.one, R.drawable.two, R.drawable.three,
        R.drawable.four, R.drawable.five, R.drawable.six
    )

    private val _diceImages = MutableLiveData<List<Int>>()
    val diceImages: LiveData<List<Int>> = _diceImages

    private val _isRolling = MutableLiveData(false)
    val isRolling: LiveData<Boolean> = _isRolling

    private var rollJob: Job? = null

    fun startRolling() {
        if (_isRolling.value == true) return
        _isRolling.value = true

        rollJob = viewModelScope.launch(Dispatchers.Default) {
            while (_isRolling.value == true) {
                rollDice()
            }
        }
    }

    fun stopRolling() {
        if (_isRolling.value == false) return
        _isRolling.value = false
        rollJob?.cancel()
    }

    private fun rollDice() {
        _diceImages.postValue(List(5) { diceImagesArray.random() })
    }
}
