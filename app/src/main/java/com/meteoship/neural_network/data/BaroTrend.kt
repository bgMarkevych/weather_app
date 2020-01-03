package com.meteoship.neural_network.data

enum class BaroTrend(val id: Int, val title: String) {

    STEADY(0, "Steady"),
    RAISE(1, "Raise"),
    FAILING(2, "Falling");

    companion object {
        fun getBaroTrendByTitle(title: String): BaroTrend? {
            values().forEach {
                if (it.title == title) {
                    return it
                }
            }
            return null
        }
    }

}