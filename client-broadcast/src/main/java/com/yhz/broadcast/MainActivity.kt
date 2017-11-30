package com.yhz.broadcast

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import kotlin.properties.Delegates

class MainActivity : Activity(), BroadcastCallback {

    private var receiver: MyBroadcast? = null
    private var btn by Delegates.notNull<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn = Button(this).apply {
            text = "send broadcast to service"
            setOnClickListener {
                val intent = Intent("$packageName.Broadcast")
                intent.putExtra("str", "Broadcast by Client")
                sendBroadcast(intent)
            }
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
        // TODO
    }
}
