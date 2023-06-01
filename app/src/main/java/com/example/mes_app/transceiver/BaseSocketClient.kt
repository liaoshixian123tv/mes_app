package com.uic.posapp.transceiver

import com.example.mes_app.errcode.ErrCode


interface SokcetClientRecvMsgListener<ErrCode, T> {

    fun onRecvMsg(status: ErrCode, msg: T)
}

interface BaseSocketClient {

    fun init(ipAddr: String, ipPort: Int, connTimeout: Int, sendTimeout: Int, recvTimeout: Int)
    fun start()
    fun connect(): ErrCode
    fun disconnect(): ErrCode
    fun sendMsg(data: String): ErrCode
    fun setRecvMsgListener(listener: SokcetClientRecvMsgListener<ErrCode, String>)
}