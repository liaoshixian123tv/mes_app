package com.example.mes_app.ui.productflow

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mes_app.Constant
import com.example.mes_app.Global
import com.example.mes_app.R
import com.example.mes_app.databinding.AddManufactureOrderViewBinding
import com.example.mes_app.databinding.FragmentProFlowBinding
import com.example.mes_app.event.StartBusinessEvent
import com.example.mes_app.ui.TitleBarView
import org.greenrobot.eventbus.EventBus
import java.util.*


class ProFlowFragment : Fragment() {

    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentProFlowBinding? = null
//    private var dialogBinding: AddManufactureOrderViewBinding? = null


    private val binding get() = _binding!!
    private lateinit var itemAdapter: FunctionItemAdapter
    private lateinit var itemListView: RecyclerView


    private val itemClickListener = object : FunctionClickListener<FunctionItem> {

        override fun onClick(view: View?, data: FunctionItem, position: Int) {
            when (data.id) {
                Constant.PRODUCT_FLOW_ADD -> addManufactureOrder()
                Constant.PRODUCT_FLOW_SHOW -> {
                    findNavController().navigate(R.id.action_proFlowFragment_to_manufactureOrderListFragment)
                }

            }
            Log.d(TAG, "id: " + data.id)


            EventBus.getDefault().post(StartBusinessEvent(data.id))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProFlowBinding.inflate(inflater, container, false)
        var root: View = binding.root

        itemListView = binding.mainPageProFlowRecyclerView
        itemAdapter = FunctionItemAdapter(setItemList(resources))
        itemAdapter.setOnDetailClickListener(itemClickListener)
        itemListView.setHasFixedSize(true)

        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, Constant.MAX_CATEGORY_ROW_SIZE)
        itemListView.layoutManager = layoutManager
        itemListView.itemAnimator = DefaultItemAnimator()
        itemListView.adapter = itemAdapter

        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TitleBarView.setTitleText("Product Flow")
        TitleBarView.setTitleBackButtonVisable(true)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
        _binding = null
    }

    private fun addManufactureOrder() {
        val dialogBinding = AddManufactureOrderViewBinding.inflate(LayoutInflater.from(context))
        val addDialog = AlertDialog.Builder(context).create()

        var currentYear = 0
        var currentMonth = 0
        var currentDay = 0

        addDialog.setView(dialogBinding.root)
        dialogBinding.addMoNumber1.setOnClickListener { _ ->
            val c = Calendar.getInstance()

            val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                c.set(year, month, day)
                currentYear = year
                currentMonth = month
                currentDay = day
                dialogBinding.addMoNumber1.setText(year.toString() + "-" + (month + 1).toString() + "-" + day.toString())
            }

            if (currentYear == 0) {
                currentYear = c.get(Calendar.YEAR)
            }

            if (currentMonth == 0) {
                currentMonth = c.get(Calendar.MONTH)
            }

            if (currentDay == 0) {
                currentDay = c.get(Calendar.DAY_OF_MONTH)
            }

            DatePickerDialog(
                addDialog.context, dateListener, currentYear,
                currentMonth,
                currentDay
            ).show()

        }

        dialogBinding.addMoOkBtn.setOnClickListener { v ->

            val number: String = dialogBinding.addMoNumber.text.toString()
            val amount: Int = dialogBinding.addMoAmount.text.toString().toInt()

            if (number == "") {
                Toast.makeText(context, "NUMBER EMPTY", Toast.LENGTH_SHORT).show()
            } else {
                val tmpMo = CategoryItems(number, amount)
                Global.setItem(tmpMo)
                Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show()
            }

            addDialog.dismiss()
        }

        dialogBinding.addMoCancelBtn.setOnClickListener { v ->
            addDialog.dismiss()
        }

        addDialog.create()
        addDialog.show()
    }


    private fun setItemList(resources: Resources): List<FunctionItem> {
        return listOf(
            FunctionItem(
                id = Constant.PRODUCT_FLOW_ADD,
                mainDispText = "Add Mo",
                mainDispIconBmp = BitmapFactory.decodeResource(
                    resources,
                    R.drawable.manufacture_order_add
                )
            ),
            FunctionItem(
                id = Constant.PRODUCT_FLOW_SHOW,
                mainDispText = "Show Mo",
                mainDispIconBmp = BitmapFactory.decodeResource(
                    resources,
                    R.drawable.show_mo
                )
            ),
            FunctionItem(
                id = Constant.PRODUCT_BLA_BLA,
                mainDispText = "BLA BLA",
                mainDispIconBmp = BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ico_general
                )
            )
        )
    }

}