package com.example.mes_app.ui.productflow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mes_app.Global
import com.example.mes_app.databinding.FragmentManufactureOrderListBinding
import com.example.mes_app.ui.TitleBarView


class ManufactureOrderListFragment : Fragment() {
    private val TAG: String = this::class.java.simpleName

    private var _binding: FragmentManufactureOrderListBinding? = null
    private val binding get() = _binding!!

    private lateinit var manufactureOrderAdapter: ManufactureOrderAdapter
    private lateinit var itemListView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManufactureOrderListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        itemListView = binding.moRecyclerView
        manufactureOrderAdapter = ManufactureOrderAdapter(Global.getArray().toList())
        itemListView.setHasFixedSize(true)
        itemListView.layoutManager = LinearLayoutManager(context)
        itemListView.adapter = manufactureOrderAdapter


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TitleBarView.setTitleText("Manufacture Order List")
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


}