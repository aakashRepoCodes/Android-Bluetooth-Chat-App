package com.example.androidblechatapp.domain

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import com.example.androidblechatapp.BluetoothPairedDevicesReciever
import com.example.androidblechatapp.Manifest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("MissingPermission")
class AppBluetoothController(
    private val context: Context
) : BluetoothController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val _scannedDevice = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val scannedDevice: StateFlow<List<BluetoothDeviceDomain>>
        get() = _scannedDevice.asStateFlow()

    private val _pairedDevice = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val pairedDevice: StateFlow<List<BluetoothDeviceDomain>>
        get() = _pairedDevice.asStateFlow()

    private val bleBroadcastReceiver = BluetoothPairedDevicesReciever { device ->
        val newDevice = device.toBluetoothDomainDevice()
        _scannedDevice.update { devices ->
            if (newDevice in devices) devices else devices + newDevice
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun startDiscovery() {
     if (!hasPermissions(android.Manifest.permission.BLUETOOTH_SCAN)) return

        context.registerReceiver(bleBroadcastReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
        updatePairedDevices()
        bluetoothAdapter?.startDiscovery()
    }

    override fun stopDiscovery() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    fun hasPermissions(permission: String) :Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun updatePairedDevices() {
        if (!hasPermissions(android.Manifest.permission.BLUETOOTH_CONNECT)) return

        bluetoothAdapter?.bondedDevices
            ?.map {
                it.toBluetoothDomainDevice()
            }.also {
                _pairedDevice.update { it }
            }
    }


}