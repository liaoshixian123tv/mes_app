package com.example.mes_app.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.mes_app.Constant
import com.example.mes_app.R
import com.example.mes_app.errcode.ErrCode
import com.example.mes_app.utility.TimerThread
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanQRView : BaseView, ZXingScannerView.ResultHandler {

    private lateinit var context: Context

    private val TAG: String = this::class.java.simpleName
    private lateinit var scannerLayout: LinearLayout
    private lateinit var cameraLayout: FrameLayout
    private var mScannerView: ZXingScannerView? = null

    override fun initView(context: Context, rootView: View) {
        this.context = context
//        TitleBarView.setTitleText("QR Scan")
//        TitleBarView.setTitleBackButtonVisable(true)
        scannerLayout = rootView.findViewById(R.id.qr_scanner_layout)
        cameraLayout = rootView.findViewById(R.id.display_camera_layout)
        mScannerView = ZXingScannerView(context)
        cameraLayout.addView(mScannerView)

    }


    override fun clearView() {
        TODO("Not yet implemented")
    }

    override fun dispView(dispDataObject: Any) {
        scannerLayout.visibility = View.VISIBLE
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
        Log.d(TAG, "dispView Camera start")
    }

    override fun stopView() {
        scannerLayout.visibility = View.INVISIBLE
        mScannerView?.stopCamera()
        Log.d(TAG, "stopView Camera stop")
    }

    override fun hideView() {
        scannerLayout.visibility = View.INVISIBLE
        mScannerView?.stopCamera()
        Log.d(TAG, "hideView")
    }

    override fun resumeView() {
        if (scannerLayout.visibility == View.VISIBLE) {
            mScannerView?.setResultHandler(this)
            mScannerView?.startCamera()
            Log.d(TAG, "resume view")
        }
    }

    override fun handleResult(rawResult: Result?) {
        Log.i(TAG, "handleResult: " + rawResult.toString())
        QRCodeController.result = rawResult.toString()
        QRCodeController.actionType = Constant.USER_QR_SCAN_COMPLETE
        hideView()
    }
}


object QRCodeController {
    private val TAG: String = this::class.java.simpleName
    var result = ""
    var actionType: Int = 0
    var timeoutSec: Int = Constant.USER_OPER_TIMEOUT_30
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
                Constant.USER_PRESS_TIMEOUT -> {
                    Log.d(TAG, "USER_PRESS_TIMEOUT")
                    ret = ErrCode.ERR_TIMEOUT
                    break
                }
                Constant.USER_QR_SCAN_COMPLETE -> {
                    Log.d(TAG, "USER_QR_SCAN_COMPLETE")
                    break
                }
            }
        } while (true)
        timerThread.stopTimer()
        return ret
    }


}