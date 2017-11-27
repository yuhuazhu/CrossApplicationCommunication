package com.yhz.messenger

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Button
import android.os.Messenger



class MainActivity : Activity() {

    var mService: Messenger? = null

    val conn = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            mService = null
        }

        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            Log.e("onServiceConnected", "ok")
            mService = Messenger(service)
        }

    }

    class ReplyHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            when(msg?.what) {
                0 -> { Log.e("handleMessage", "服务端回来的消息") }
                else -> super.handleMessage(msg)
            }
        }
    }
    var replyMessenger = Messenger(ReplyHandler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(Button(this)) {
            text = "点击显示名字"
            setOnClickListener {
                val msg = Message.obtain()
                msg.replyTo = replyMessenger
                val bundle = Bundle()
                bundle.putString("data", "我要名字")
                msg.data = bundle
                try {
                    mService?.send(msg)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
            setContentView(this)
        }
    }

    override fun onStart() {
        super.onStart()
        val service = Intent("com.yhz.service.MessengerService")
        service.`package` = "com.yhz.service"
        bindService(service, conn, Context.BIND_AUTO_CREATE)
    }
}
