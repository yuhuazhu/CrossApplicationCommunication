package com.yhz.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AIDLService : Service() {

    private val mBinder: IRemoteService.Stub = object : IRemoteService.Stub() {

        override fun getString(): String {
            return "this AIDL Callback"
        }
    }

    override fun onBind(p0: Intent?): IBinder? = mBinder

}