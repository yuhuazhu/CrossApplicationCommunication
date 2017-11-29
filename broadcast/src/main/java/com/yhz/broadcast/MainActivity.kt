package com.yhz.broadcast

import android.app.Activity
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import kotlin.properties.Delegates

class MainActivity : Activity(), BroadcastCallback {

    private var receiver: MyBroadcast? = null
    private var tv by Delegates.notNull<TextView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv = TextView(this).apply {
            text = "wait service broadcast"
            setContentView(this)
        }
        val receiver = MyBroadcast(this)
        val filter = IntentFilter("com.yhz.service.Broadcast")
        registerReceiver(receiver, filter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun broadcastComing(string: String) {
        tv.text = string
    }
}
