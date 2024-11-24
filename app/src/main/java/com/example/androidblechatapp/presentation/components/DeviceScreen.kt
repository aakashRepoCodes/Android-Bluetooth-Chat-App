package com.example.androidblechatapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblechatapp.domain.BluetoothDevice
import com.example.androidblechatapp.presentation.BluetoothUiState

@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    startScan: () -> Unit,
    stopScan: () -> Unit,
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(onClick = startScan) {
                Text(text = "Start scan")
            }

            Button(onClick = stopScan) {
                Text(text = "Stop scan")
            }
        }
    }
}

@Composable
fun DevicesListView(
    pairedDevicesList : List<BluetoothDevice>,
    scannedDevicesList : List<BluetoothDevice>
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item{
            Text(
                text = "Paired Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(12.dp)
            )

        }
        items(pairedDevicesList) { item ->
            Text(
                text = item.deviceName,
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
                    .clickable {  },
                fontSize = 19.sp
            )
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item{
            Text(
                text = "Scanned Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(12.dp)
            )

        }
        items(scannedDevicesList) { item ->
            Text(
                text = item.deviceName,
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
                    .clickable {  },
                fontSize = 19.sp
            )
        }
    }

}