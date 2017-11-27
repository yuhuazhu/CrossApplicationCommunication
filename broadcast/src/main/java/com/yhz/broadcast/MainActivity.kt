package com.yhz.broadcast

import android.app.Activity
import android.content.IntentFilter
import android.os.Bundle

class MainActivity : Activity() {

    private var receiver: MyBroadcast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receiver = MyBroadcast()
        val filter = IntentFilter("com.yhz.service.Broadcast")
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
