package com.example.mes_app.errcode

import android.content.Context
import com.example.mes_app.R

enum class ErrCode(msgId: Int, timeoutSec: Int) {
    NO_ERR(R.string.err_user_cancel, 0),
    ERR_USER_CANCEL(R.string.err_user_cancel, 0),
    ERR_TIMEOUT(R.string.err_timeout, 10),
    ERR_COMM_RECEIVE_TIMEOUT(R.string.err_comm_receive_timeout, 5),
    ERR_COMM_OPEN_FAIL(R.string.err_comm_open_failed, 5),
    ERR_COMM_CONNECT_FAIL(R.string.err_comm_connect_failed, 5),
    ERR_COMM_DISCONNECT_FAIL(R.string.err_comm_disconnect_failed, 5),
    ERR_COMM_SEND_FAIL(R.string.err_comm_send_failed, 5),
    ERR_COMM_RECEIVE_FAIL(R.string.err_comm_receive_failed, 5);


    private val labelID: Int = msgId

    fun getMsg(context: Context) =
        context.getString(labelID)
}