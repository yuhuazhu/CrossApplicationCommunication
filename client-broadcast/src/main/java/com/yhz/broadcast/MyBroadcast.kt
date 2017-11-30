package com.yhz.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcast(private val callback: BroadcastCallback) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("MyBroadcast", "onReceive")
        val content = intent?.getStringExtra("str") ?: "null"
        callback.broadcastComing(content)
        Toast.makeText(context, "got $content !", Toast.LENGTH_SHORT).show()
    }
}