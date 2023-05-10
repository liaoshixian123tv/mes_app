package com.example.mes_app.ui.productflow

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mes_app.R

data class FunctionItem internal constructor(
    var id: String,
    var mainDispText: String,
    var mainDispIconBmp: Bitmap
)

interface FunctionClickListener<T> {
    fun onClick(view: View?, data: T, position: Int)
}

class FunctionItemAdapter internal constructor(private var itemList: List<FunctionItem>) :
    RecyclerView.Adapter<FunctionItemAdapter.FunctionItemViewHolder>() {

    private var clickListener: FunctionClickListener<FunctionItem>? = null

    inner class FunctionItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemText: TextView = itemView.findViewById(R.id.function_item_title)
        var itemImage: ImageView = itemView.findViewById(R.id.function_item_image)
        val itemLayout: CardView = itemView.findViewById(R.id.function_item_main_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionItemViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.function_item_view, parent, false)
        return FunctionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FunctionItemViewHolder, position: Int) {
        if (itemList == null || itemList!!.isEmpty())
            return
        val item = itemList!![position]
        holder.itemImage.setImageBitmap(item.mainDispIconBmp)
        holder.itemText.text = item.mainDispText
        holder.itemLayout.setOnClickListener { v ->
            clickListener!!.onClick(v, item, position)
        }
    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }

    fun setItemList(itemList: List<FunctionItem>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    fun setOnDetailClickListener(clickListener: FunctionClickListener<FunctionItem>) {
        this.clickListener = clickListener
    }

}