package com.yhz.service

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.content.IntentFilter
import android.widget.TextView
import kotlin.properties.Delegates


class MainActivity : Activity(), BroadcastCallback {

    private var receiver: MyBroadcast? = null
    private var tv by Delegates.notNull<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv = TextView(this).apply {
            text = "wait client～"
            setContentView(this)
        }
        register()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    private fun register() {
        receiver = MyBroadcast(this)
        val filter = IntentFilter("com.yhz.broadcast.Broadcast")
        registerReceiver(receiver, filter)
    }

    override fun broadcastComing(string: String) {
        val intent = Intent("$packageName.Broadcast")
        intent.putExtra("str", "Broadcast by Service")
        sendBroadcast(intent)
    }

    override fun onBackPressed() {
        moveTaskToBack(false)
    }
}
