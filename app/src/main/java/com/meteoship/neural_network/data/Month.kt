package com.meteoship.neural_network.data

enum class Month(val order: Int, val title: String) {
    JANUARY(1, "January"),
    FEBRUARY(2, "February"),
    MARCH(3, "March"),
    APRIL(4, "April"),
    MAY(5, "May"),
    JUNE(6, "June"),
    JULY(7, "July"),
    AUGUST(8, "August"),
    SEPTEMBER(9, "September"),
    OCTOBER(10, "October"),
    NOVEMBER(11, "November"),
    DECEMBER(12, "December");

    companion object {
        fun getMonthByTitle(title: String): Month? {
            values().forEach {
                if (it.title == title) {
                    return it
                }
            }
            return null
        }
    }
}