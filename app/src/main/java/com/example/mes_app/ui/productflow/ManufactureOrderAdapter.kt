package com.example.mes_app.ui.productflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mes_app.R

// TODO(modify the attribute of category)
data class CategoryItems internal constructor(
    var number: String = "",
    var amount: Int = 0,
)


class ManufactureOrderAdapter internal constructor(private var itemsList: List<CategoryItems>) :
    RecyclerView.Adapter<ManufactureOrderAdapter.CategoryItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemsViewHolder {
        // TODO("replace cardview layout")
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.manufacture_order_list_view, parent, false)

        return CategoryItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryItemsViewHolder, position: Int) {

        if (itemsList == null || itemsList!!.isEmpty())
            return

        val item = itemsList!![position]
        holder.number.text = item.number
        holder.amount.text = item.amount.toString()

    }

    override fun getItemCount(): Int {
        if (itemsList == null || itemsList!!.isEmpty())
            return 0

        return itemsList!!.size
    }

    fun setItemList(itemsList: List<CategoryItems>) {
        this.itemsList = itemsList
        notifyDataSetChanged()
    }


    inner class CategoryItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO(replace the item view id and bind the category attribute)
        var number: TextView = itemView.findViewById(R.id.mo_number_value)
        var amount: TextView = itemView.findViewById(R.id.mo_amount_value)
        val settingItemLayout: CardView =
            itemView.findViewById(R.id.manufacture_order_card_view_layout)

    }
}