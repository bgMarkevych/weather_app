package com.meteoship.model.data

import com.meteoship.R

enum class SheepIcon(val degrees: Array<Int>, val drawableId: Int) {
    SH_MINUS_5(arrayOf(0), R.drawable.ic_sheep_minus_5_degree),
    SH_5(arrayOf(1, 5), R.drawable.ic_sheep_5_degree),
    SH_10(arrayOf(6, 10), R.drawable.ic_sheep_10_degree),
    SH_15(arrayOf(11, 15), R.drawable.ic_sheep_15_degree),
    SH_20(arrayOf(16, 25), R.drawable.ic_sheep_20_degree),
    SH_25(arrayOf(21, 25), R.drawable.ic_sheep_25_degree),
    SH_30(arrayOf(26), R.drawable.ic_sheep_30_degree);

    companion object {
        fun getIconByTemp(temp: Int): Int {
            var item: SheepIcon? = null
            values().forEach {
                if (it.degrees.size != 1 && temp >= it.degrees[0] && temp <= it.degrees[1]) {
                    item = it
                }
            }
            if (item == null) {
                values().forEach {
                    item = if (SH_MINUS_5.degrees[0] <= temp) {
                        SH_MINUS_5
                    } else {
                        SH_30
                    }
                }
            }
            return item!!.drawableId
        }
    }
}