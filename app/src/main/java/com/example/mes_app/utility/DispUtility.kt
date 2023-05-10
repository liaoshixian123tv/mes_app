package com.example.mes_app.utility

import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.Gravity
import android.widget.Toast
import com.example.mes_app.Constant
import com.example.mes_app.errcode.ErrCode
import com.example.mes_app.ui.SelectScheduleTypeController

class DispUtility(
    context: Context,
    uiHandler: Handler
) {

    private val TAG: String = this::class.java.simpleName

    private var context: Context = context
    private var uiHandler: Handler = uiHandler


    interface selectScheduleTypeListener<ErrCode, T> {

        fun onSelectScheduleTypeResult(status: ErrCode, stringText: T)
    }


    fun dispScheduleSelection(
        listener: selectScheduleTypeListener<ErrCode, String>
    ): ErrCode {
        var msg = Message()
        msg.what = Constant.ACTION_DISPLAY_SCHEDULE
        uiHandler.sendMessage(msg)
        var ret = SelectScheduleTypeController.waitResult()
        if (ret != ErrCode.NO_ERR) {
            if (ret == ErrCode.ERR_TIMEOUT) {
                var tost = Toast.makeText(context, "Timeout", Toast.LENGTH_LONG)
                tost.setGravity(Gravity.CENTER, 0, 0)
                tost.show()
            }
            return ret
        }
        var result = SelectScheduleTypeController.selection

        listener.onSelectScheduleTypeResult(ret, result)
        return ret
    }

}