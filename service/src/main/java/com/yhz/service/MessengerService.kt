package com.yhz.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

class MessengerService : Service() {

    class IncomingHandler : Handler() {

        override fun handleMessage(msg: Message?) {
            when(msg?.what) {
                0 -> {
                    val tempBundle = msg.data
                    val str = tempBundle.getString("str") + "by Service"
                    Log.e("handleMessage", "客户端发过来的$str")
                    val message = Message.obtain()
                    val bundle = Bundle()
                    bundle.putString("str", str)
                    message.data = bundle
                    msg.replyTo.send(message)
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    private val mMessenger = Messenger(IncomingHandler())

    override fun onBind(p0: Intent?): IBinder = mMessenger.binder
}