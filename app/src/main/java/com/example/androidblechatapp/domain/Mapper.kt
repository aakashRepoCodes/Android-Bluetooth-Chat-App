package com.example.androidblechatapp.domain

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice


@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDomainDevice() : BluetoothDeviceDomain {

    return BluetoothDeviceDomain(
        deviceName = name.orEmpty(),
        address = address
    )
}