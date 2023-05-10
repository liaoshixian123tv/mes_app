package com.example.mes_app.controller

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.mes_app.Constant
import com.example.mes_app.errcode.ErrCode
import com.example.mes_app.model.WALAWALA
import com.example.mes_app.ui.TitleBarView
import com.example.mes_app.utility.AppUtility

class ProFlowCtrl(
    context: Context,
    uiHandler: Handler
) : Runnable {
    private var TAG = this::class.java.simpleName
    private val context = context
    private val uiHandler = uiHandler
    private val appUtil: AppUtility = AppUtility(context, uiHandler)


    override fun run() {
        var info = WALAWALA()
        var ret: ErrCode = ErrCode.NO_ERR
        TitleBarView.setTitleText("Schedule")
        do {
            Log.d(TAG, "Welcome to ProFlowCtrl")
            ret = appUtil.getScheduleSelection(info)
            if (ret != ErrCode.NO_ERR) {
                Log.d(TAG, info.toString())
                break
            }
        } while (false)
        finishFlow(ret)
    }


    fun finishFlow(errCode: ErrCode) {
        val msg = Message()
        msg.what = Constant.ACTION_FINISH
        uiHandler.sendMessage(msg)
    }

}