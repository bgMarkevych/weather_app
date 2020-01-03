package com.meteoship.neural_network.data

enum class Wind(val title: String) {
    CALM("Calm"),
    N("N"),
    NNE("NNE"),
    NE("NE"),
    ENE("ENE"),
    E("E"),
    ESE("ESE"),
    SE("SE"),
    SSE("SSE"),
    S("S"),
    SSW("SSW"),
    SW("SW"),
    WSW("WSW"),
    W("W"),
    WNW("WNW"),
    NW("NW"),
    NNW("NNW");

    companion object {
        fun getWindByTitle(title: String): Wind? {
            values().forEach {
                if (it.title == title) {
                    return it
                }
            }
            return null
        }
    }
}