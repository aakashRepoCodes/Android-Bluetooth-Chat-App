package com.example.androidblechatapp.domain

import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {

    val scannedDevice : StateFlow<List<BluetoothDevice>>

    val pairedDevice : StateFlow<List<BluetoothDevice>>

    fun startDiscovery()

    fun stopDiscovery()

    fun release()
}