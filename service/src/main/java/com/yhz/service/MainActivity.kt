package com.yhz.service

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.widget.Button


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Button(this).apply {
            text = "这是服务端，点击发送广播"
            setContentView(this)
            setOnClickListener {
                val intent = Intent("$packageName.Broadcast")
                intent.putExtra("str", "Broadcast by Service")
                sendBroadcast(intent)
            }
        }
    }
}
