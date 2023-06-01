package com.example.mes_app.ui

import android.content.Context
import android.view.View

interface BaseView {
    fun initView(context: Context, rootView: View)
    fun clearView()
    fun dispView(dispDataObject: Any)
    fun stopView()
    fun hideView()
    fun resumeView()
}