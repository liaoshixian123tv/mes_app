package com.example.mes_app.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.*
import com.example.mes_app.Constant
import com.example.mes_app.R
import com.example.mes_app.errcode.ErrCode
import com.example.mes_app.utility.TimerThread

class ScheduleViewData {
    var dispText: String = ""
    var timeoutSec: Int = Constant.USER_OPER_TIMEOUT
}


class ScheduleView : BaseView {
    private lateinit var scheudleViewLayout: LinearLayout
    private lateinit var scheduleRadioGroup: RadioGroup
    private lateinit var scheduleCancelBtn: ImageButton
    private lateinit var scheduleOKBtn: ImageButton


    private lateinit var context: Context

    private val TAG: String = this::class.java.simpleName

    override fun initView(context: Context, rootView: View) {
        this.context = context

        scheudleViewLayout = rootView.findViewById(R.id.add_shceudle_view_main_layout)
        scheduleRadioGroup = scheudleViewLayout.findViewById(R.id.schedule_radio_group_view)
        scheduleCancelBtn = scheudleViewLayout.findViewById(R.id.schedule_cancel_image_btn)
        scheduleOKBtn = scheudleViewLayout.findViewById(R.id.schedule_ok_image_btn)

        //(set cancel listener)
        scheduleCancelBtn.setOnClickListener { v ->
            SelectScheduleTypeController.actionType = Constant.USER_PRESS_CANCEL_BUTTON
        }
        //(set ok listener)
        scheduleOKBtn.setOnClickListener { v ->
            var btnID: Int = scheduleRadioGroup.checkedRadioButtonId

            if (btnID == R.id.selection_a_radio) {
                SelectScheduleTypeController.selection = "A"
            } else if (btnID == R.id.selection_b_radio) {
                SelectScheduleTypeController.selection = "B"
            }
            SelectScheduleTypeController.actionType = Constant.USER_PRESS_OK_BUTTON
        }
    }

    override fun clearView() {
    }

    override fun dispView(dispDataObject: Any) {
//        var tmpDisp: ScheduleViewData = dispDataObject as ScheduleViewData

        scheudleViewLayout.visibility = View.VISIBLE
    }

    override fun stopView() {
        scheduleCancelBtn.performClick()
    }

    override fun hideView() {
        scheudleViewLayout.visibility = View.INVISIBLE
    }

    override fun resumeView() {
        Log.d(TAG, "resumeView")
    }


}


object SelectScheduleTypeController {
    private val TAG: String = this::class.java.simpleName
    var selection: String = ""
    var actionType: Int = 0
    var timeoutSec: Int = Constant.USER_OPER_TIMEOUT_10
    fun waitResult(): ErrCode {
        var ret: ErrCode = ErrCode.NO_ERR
        var timerThread = TimerThread()
        this.actionType = 0

        timerThread.startTimer(this.timeoutSec)

        do {
            if (timerThread.isTimeout() == true) {
                actionType = Constant.USER_PRESS_TIMEOUT
            }

            when (actionType) {
                Constant.USER_PRESS_OK_BUTTON -> {
                    Log.d(TAG, "USER_PRESS_OK_BUTTON")
                    ret = ErrCode.NO_ERR
                    break
                }
                Constant.USER_PRESS_CANCEL_BUTTON -> {
                    Log.d(TAG, "USER_PRESS_CANCEL_BUTTON")
                    ret = ErrCode.ERR_USER_CANCEL
                    break
                }
                Constant.USER_PRESS_TIMEOUT -> {
                    Log.d(TAG, "USER_PRESS_TIMEOUT")
                    ret = ErrCode.ERR_TIMEOUT
                    break
                }
            }
        } while (true)
        timerThread.stopTimer()
        return ret
    }


}