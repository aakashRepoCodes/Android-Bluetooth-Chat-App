package com.example.androidblechatapp.domain

typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice (
    val deviceName: String,
    val address: String
)

