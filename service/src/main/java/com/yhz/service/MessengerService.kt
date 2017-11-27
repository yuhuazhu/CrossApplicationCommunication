package com.yhz.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log

class MessengerService : Service() {

    class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            when(msg?.what) {
                0 -> {
                    Log.e("handleMessage", "客户端发过来的消息")
                    val message = Message.obtain()
                    msg.replyTo.send(message)
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    private val mMessenger = Messenger(IncomingHandler())

    override fun onBind(p0: Intent?): IBinder = mMessenger.binder
}