package com.example.mes_app

import android.app.Application
import com.example.mes_app.ui.productflow.CategoryItems

class Global : Application() {
    companion object {
        private val moArray = ArrayList<CategoryItems>()

        fun getArray(): ArrayList<CategoryItems> {
            return moArray
        }

        fun setItem(info: CategoryItems) {
            this.moArray.add(info)
        }
    }


}