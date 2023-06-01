package com.example.mes_app.ui.productflow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mes_app.R
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanQRFragment : Fragment(), ZXingScannerView.ResultHandler {

    private val TAG: String = this::class.java.simpleName

    //    private var mScannerView: ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan_qr, container, false)
    }

    override fun handleResult(rawResult: Result?) {
        TODO("Not yet implemented")
    }
}