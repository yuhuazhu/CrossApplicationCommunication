package com.yhz.aidl

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.yhz.service.IRemoteService


class MainActivity : Activity() {

    private var mRemoteService: IRemoteService? = null

    private val mConnection = object : ServiceConnection {

        override fun onServiceDisconnected(p0: ComponentName?) {
            mRemoteService = null
        }

        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            Log.e("AIDL", "onServiceConnected")
            mRemoteService = IRemoteService.Stub.asInterface(service)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(Button(this)) {
            text = "click here"
            setOnClickListener { (it as Button).text = mRemoteService?.string }
            setContentView(this)
        }
    }

    override fun onStart() {
        super.onStart()
        val service = Intent("com.yhz.service.AIDLService")
        service.`package` = "com.yhz.service"
        bindService(service, mConnection, Context.BIND_AUTO_CREATE)
    }
}
