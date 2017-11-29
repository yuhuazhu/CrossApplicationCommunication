package com.yhz.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcast(private val callback: BroadcastCallback) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("MyBroadcast", "onReceive")
        callback.broadcastComing(intent?.getStringExtra("str") ?: "null")
        Toast.makeText(context, "got Broadcast!", Toast.LENGTH_SHORT).show()
    }
}