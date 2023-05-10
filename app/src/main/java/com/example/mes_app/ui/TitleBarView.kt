package com.example.mes_app.ui

import android.util.Log
import android.view.View
import com.example.mes_app.Constant
import com.example.mes_app.databinding.TitleBarViewBinding
import com.example.mes_app.event.ActionEvent
import org.greenrobot.eventbus.EventBus

object TitleBarView {
    private val TAG = TitleBarView::class.java.simpleName
    private lateinit var titleBinding: TitleBarViewBinding

    private var currentText: String = ""
    private var currentBackBtnState: Int = View.VISIBLE


    fun setTitleBarView(titleBarViewBinding: TitleBarViewBinding) {
        titleBinding = titleBarViewBinding
        titleBinding?.backBtn.setOnClickListener { v ->
            EventBus.getDefault().post(ActionEvent(Constant.ACTION_BACK))
        }
    }

    fun setTitleText(titleText: String) {
        titleBinding?.headerTitleText?.text = titleText
        Log.d(TAG, "Title Text: " + titleText)
        titleBinding?.backBtn.visibility = View.INVISIBLE
        titleBinding?.headerTitleText?.setBackgroundResource(android.R.color.transparent)

    }

    fun setTitleBackButtonVisable(isVisable: Boolean) {

        if (isVisable) {
            titleBinding?.backBtn!!.visibility = View.VISIBLE

        } else {
            titleBinding?.backBtn!!.visibility = View.INVISIBLE
        }
    }

    fun saveMainPageTitleBar() {
        this.currentText = titleBinding?.headerTitleText?.text.toString()
        this.currentBackBtnState = titleBinding?.backBtn!!.visibility
    }

    fun rollbackMainPageTitleBar() {
        titleBinding?.headerTitleText?.text = this.currentText
        titleBinding?.backBtn!!.visibility = this.currentBackBtnState
    }

    fun setTitleBackgroundInvisable() {
        titleBinding?.headerTitleText?.setBackgroundResource(android.R.color.transparent)

    }
}