package com.yhz.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.e("MyBroadcast", "onReceive")
//        Toast.makeText(p0, "got it!", Toast.LENGTH_SHORT).show()
    }
}