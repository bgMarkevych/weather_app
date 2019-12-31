package com.meteoship.app.fragment.forecast_info

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.meteoship.R
import com.meteoship.model.data.ForecastInfoDataItem

class ForecastInfoAdapter(private val data: List<ForecastInfoDataItem>) :
    RecyclerView.Adapter<ForecastInfoAdapter.ForecastInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastInfoViewHolder {
        return ForecastInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.forecast_info_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ForecastInfoViewHolder, position: Int) {
        val item = data[position]
        holder.title!!.setText(item.name)
        holder.value!!.text = item.value
        holder.value!!.setTextColor(ContextCompat.getColor(holder.itemView.context, item.colorInt))
    }


    inner class ForecastInfoViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {
            title = itemView.findViewById(R.id.title)
            value = itemView.findViewById(R.id.value)
        }

        var title: TextView? = null
        var value: TextView? = null

    }

}