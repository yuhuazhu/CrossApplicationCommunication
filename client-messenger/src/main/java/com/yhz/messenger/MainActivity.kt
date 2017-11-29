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
import kotlin.properties.Delegates


class MainActivity : Activity(), ServiceCallback {
    override fun updateStr(string: String) {
        btn.text = string
    }

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

    class ReplyHandler(val callback: ServiceCallback) : Handler() {
        override fun handleMessage(msg: Message?) {
            when(msg?.what) {
                0 -> {
                    Log.e("handleMessage", "服务端回来的消息")
                    val bundle = msg.data
                    callback.updateStr(bundle.getString("str"))
                }
                else -> super.handleMessage(msg)
            }
        }
    }
    var replyMessenger = Messenger(ReplyHandler(this))

    var count = 0
    var btn: Button by Delegates.notNull()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn = Button(this).apply {
            text = "获取点击次数"
            setOnClickListener {
                val msg = Message.obtain()
                msg.replyTo = replyMessenger
                val bundle = Bundle()
                bundle.putString("str", "第 ${++count} 次点击")
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
