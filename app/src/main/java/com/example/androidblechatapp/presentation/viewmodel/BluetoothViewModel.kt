package com.example.androidblechatapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidblechatapp.domain.BluetoothController
import com.example.androidblechatapp.presentation.BluetoothUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
): ViewModel() {

    private val _uiState = MutableStateFlow(BluetoothUiState())

    val state = combine(
        bluetoothController.pairedDevice,
        bluetoothController.scannedDevice,
        _uiState
    ) { scannedDevices,  pairedDevices , state ->
        state.copy(
            pairedDevices = pairedDevices,
            scannedDevices = scannedDevices
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    fun startScan() = bluetoothController.startDiscovery()

    fun stopScan() = bluetoothController.stopDiscovery()

}