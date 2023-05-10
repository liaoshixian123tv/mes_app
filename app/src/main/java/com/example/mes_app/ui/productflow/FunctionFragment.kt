package com.example.mes_app.ui.productflow

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mes_app.Constant
import com.example.mes_app.R
import com.example.mes_app.databinding.FragmentFunctionBinding
import com.example.mes_app.ui.TitleBarView


class FunctionFragment : Fragment() {


    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentFunctionBinding? = null

    private val binding get() = _binding!!

    private lateinit var functionItemAdapter: FunctionItemAdapter
    private lateinit var itemListView: RecyclerView


    private val itemClickListener = object : FunctionClickListener<FunctionItem> {
        override fun onClick(view: View?, data: FunctionItem, position: Int) {
            Log.d(TAG, "function list id: " + data.id)
            when (data.id) {
                Constant.PRODUCT_FLOW -> {
                    var bundle = bundleOf(Constant.PRODUCT_FLOW_TYPE to data.id)
                    findNavController().navigate(R.id.action_function_main_page_to_proFlowFragment)
//                    EventBus.getDefault().post(StartBusinessEvent(data.id))
                }
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFunctionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 綁定adapter
        itemListView = binding.mainPageFunctionRecyclerView
        functionItemAdapter = FunctionItemAdapter(setItemList(resources))
        functionItemAdapter.setOnDetailClickListener(itemClickListener)

        itemListView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, Constant.MAX_CATEGORY_ROW_SIZE)
        itemListView.layoutManager = layoutManager
        itemListView.itemAnimator = DefaultItemAnimator()
        itemListView.adapter = functionItemAdapter

        TitleBarView.setTitleText("Function")
        TitleBarView.setTitleBackButtonVisable(false)

        return root
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setItemList(resources: Resources): List<FunctionItem> {
        return listOf(
            FunctionItem(
                id = Constant.PRODUCT_FLOW,
                mainDispText = "Product Flow",
                mainDispIconBmp = BitmapFactory.decodeResource(
                    resources,
                    R.drawable.product_flow_icon
                )
            )
        )
    }


}