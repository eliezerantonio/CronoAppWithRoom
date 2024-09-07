package com.example.cronoapp_e_room.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cronoapp_e_room.state.CronoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CronometroViewModel : ViewModel() {

    var state by mutableStateOf(CronoState())
        private set

    var cronoJob by mutableStateOf<Job?>(null)
        private set

    var time by mutableLongStateOf(0L)
        private set

    fun onValue(value: String) {
        state = state.copy(title = value)
    }

    fun init() {
        state = state.copy(activeCrono = true)
    }

    fun pause() {
        state = state.copy(activeCrono = false, showSaveButton = true)
    }

    fun delete() {
        cronoJob?.cancel()
        time = 0;
        state = state.copy(activeCrono = false, showSaveButton = false, showTextField = false)
    }

    fun showTextField() {
        state = state.copy(showTextField = true)
    }

    fun cronos() {
        if (state.activeCrono) {
            cronoJob?.cancel()
            cronoJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    time += 1000
                }
            }
        } else {
            cronoJob?.cancel()
        }
    }
}