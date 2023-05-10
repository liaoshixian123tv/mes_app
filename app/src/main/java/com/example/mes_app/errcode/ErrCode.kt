package com.example.mes_app.errcode

import android.content.Context
import com.example.mes_app.R

enum class ErrCode(msgId: Int, timeoutSec: Int) {
    NO_ERR(R.string.err_user_cancel, 0),
    ERR_USERCANCEL(R.string.err_user_cancel, 0),
    ERR_TIMEOUT(R.string.err_timeout, 10);

    private val labelID: Int = msgId

    fun getMsg(context: Context) =
        context.getString(labelID)
}