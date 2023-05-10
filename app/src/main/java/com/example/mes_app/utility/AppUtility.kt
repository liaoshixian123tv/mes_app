package com.example.mes_app.utility

import android.content.Context
import android.os.Handler
import com.example.mes_app.errcode.ErrCode
import com.example.mes_app.model.WALAWALA

class AppUtility {

    companion object {
        private val TAG: String = this::class.java.simpleName
    }

    private lateinit var context: Context
    private lateinit var uiHandler: Handler
    private lateinit var dispUtil: DispUtility

    //  無參數時
    constructor() {

    }

    //  有這兩個參數時
    constructor(
        context: Context,
        uiHandler: Handler
    ) {
        this.context = context
        this.uiHandler = uiHandler
        this.dispUtil = DispUtility(this.context, this.uiHandler)
    }


    fun getScheduleSelection(info: WALAWALA): ErrCode {
        var ret: ErrCode = ErrCode.NO_ERR
        dispUtil.dispScheduleSelection(object :
            DispUtility.selectScheduleTypeListener<ErrCode, String> {
            override fun onSelectScheduleTypeResult(status: ErrCode, stringText: String) {
                ret = status
                info.scheduleType = stringText
            }
        })

        return ret
    }


}