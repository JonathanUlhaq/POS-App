package com.example.posapp

import android.app.Application
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var receiver:BroadcastReceiver
    fun connectedCheck(connectedDevice:MutableState<BluetoothDevice?>) {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    BluetoothDevice.ACTION_ACL_CONNECTED -> {
                        val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!
                        viewModelScope.launch(Dispatchers.Main) {
                            connectedDevice.value = device
                        }
                    }
                    BluetoothDevice.ACTION_ACL_DISCONNECTED -> {
                        viewModelScope.launch(Dispatchers.Main) {
                            connectedDevice.value = null
                        }
                    }
                }
            }
        }
    }
    init {
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
            addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        }
        application.registerReceiver(receiver,filter)

    }

    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().unregisterReceiver(receiver)
    }
}