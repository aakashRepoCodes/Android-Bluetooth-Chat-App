package com.example.androidblechatapp.presentation

import androidx.compose.ui.graphics.BlendMode
import com.example.androidblechatapp.domain.BluetoothDevice

data class BluetoothUiState (
    val pairedDevices : List<BluetoothDevice> = emptyList(),
    val scannedDevices : List<BluetoothDevice> = emptyList()
)