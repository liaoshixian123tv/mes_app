package com.example.mes_app

import android.content.Context
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mes_app.R
import com.example.mes_app.databinding.ActivityMainBinding
import com.example.mes_app.controller.ProFlowCtrl
import com.example.mes_app.event.ActionEvent
import com.example.mes_app.event.StartBusinessEvent
import com.example.mes_app.model.ManufactureOrder
import com.example.mes_app.ui.BaseView
import com.example.mes_app.ui.ScheduleView
import com.example.mes_app.ui.TitleBarView
import com.example.mes_app.ui.TitleBarView.saveMainPageTitleBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var context: Context
//    private var bundle: Bundle? = null

    private val MAIN_SCREEN_VIEW: String = "MAIN_SCREEN_VIEW"
    private var isCreatedMainScreen: Boolean = false

    private lateinit var navController: NavController
    private lateinit var rootView: View
    private lateinit var mainScreenView: LinearLayout

    var scheduleView = ScheduleView()
    var currentView: BaseView = scheduleView


    var moArray: MutableList<ManufactureOrder> = mutableListOf()

    var handlerThread = HandlerThread("functionHandler")
    lateinit var requestFunctionHandler: Handler

    val uiHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Log.d(TAG, "UI Handler: " + msg.what)
            when (msg.what) {
                Constant.ACTION_BACK -> {
                    Log.i(TAG, "Back to Last Page")
                    onActionBack()
                }
                Constant.ACTION_DISPLAY_SCHEDULE -> {
                    dispScheduleView()
                }
                Constant.ACTION_FINISH -> {
                    backToMainScreen()
                }


                // 看要顯示什麼view
                else -> super.handleMessage(msg)
            }
        }
    }

    fun startBusiness(businessType: String) {
        saveMainPageTitleBar()
        when (businessType) {
            Constant.PRODUCT_FLOW -> requestFunctionHandler.post(ProFlowCtrl(context, uiHandler))
            Constant.PRODUCT_BLA_BLA -> requestFunctionHandler.post(ProFlowCtrl(context, uiHandler))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootBody: View = binding.centralWidgetBodyLayout

        rootView = binding.root
        mainScreenView = rootBody.findViewById(R.id.main_screen_view_layout)

        scheduleView.initView(this, rootBody.findViewById(R.id.add_shceudle_view_main_layout))

        val navView: BottomNavigationView = mainScreenView.findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_activity_main_screen
        ) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        supportActionBar?.hide()
        TitleBarView.setTitleBarView(binding.mainTitleBarLayout)
        setContentView(rootView)

        if (!handlerThread.isAlive) {
            handlerThread.start()
        }
        requestFunctionHandler = Handler(handlerThread.looper)

        hideAllView()

        Log.d(TAG, Global.getArray().toString())

        displayMainScreen()

        val tmp = android.provider.Settings.Secure.getInt(
            this.contentResolver,
            android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
            0
        )

        Log.d(TAG, "lalalalaalalalalalala " + tmp.toString())

    }


    // onRestart onRestart
    override fun onRestart() {
        Log.d(TAG, "onRestart")
        isCreatedMainScreen = true
        super.onRestart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        super.onResume()
    }

    // onPause onPause
    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }

        super.onStop()
    }

    // onDestroy onDestroy
    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        requestFunctionHandler.removeCallbacksAndMessages(null)
        if (handlerThread.isAlive) {
            handlerThread.interrupt()
        }

        super.onDestroy()
    }

    // onRestoreInstanceState onRestoreInstanceState
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState")

        if (savedInstanceState != null) {
            val isMainScreenView = savedInstanceState.getBoolean(MAIN_SCREEN_VIEW, false)
            Log.d(TAG, "savedInstanceState - MainScreenView is $isMainScreenView")
        }

        super.onRestoreInstanceState(savedInstanceState)

    }

    // onSaveInstanceState onSaveInstanceState
    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState")

        if (mainScreenView.visibility == View.VISIBLE) {
            outState.putBoolean(MAIN_SCREEN_VIEW, true)
        } else {
            outState.putBoolean(MAIN_SCREEN_VIEW, false)
        }

        super.onSaveInstanceState(outState)
    }

    fun onActionBack() {
        this.onBackPressed()
    }

    override fun onBackPressed() {
        Log.d(TAG, "onBackPressed")
        super.onBackPressed()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onStartBusinessEvent(event: StartBusinessEvent) {
        Log.d(TAG, "Start Event: " + event.eventType)
        startBusiness(event.eventType)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onStartActionEvent(event: ActionEvent) {
        Log.d(TAG, "Start Action: ")
        when (event.actionType) {
            Constant.ACTION_BACK -> {
                onActionBack()
            }
        }
    }

    // hideAllView 隱藏所有的view
    private fun hideAllView() {
        scheduleView.hideView()
        mainScreenView.visibility = View.INVISIBLE

    }

    // displayMainScreen 顯示主頁面
    private fun displayMainScreen() {
        Log.d(TAG, "顯示主頁面")
        currentView.stopView()
        hideAllView()
        mainScreenView.visibility = View.VISIBLE
    }

    fun dispScheduleView() {
        hideAllView()
        scheduleView.dispView("")
        currentView = scheduleView

    }

    fun backToMainScreen() {
        if (mainScreenView.visibility == View.VISIBLE) {
            return
        }
        displayMainScreen()
        TitleBarView.rollbackMainPageTitleBar()
    }

}

