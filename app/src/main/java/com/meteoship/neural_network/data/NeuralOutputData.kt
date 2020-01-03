package com.meteoship.neural_network.data

var z_forecast = arrayOf(
    "Settled fine",
    "Fine weather",
    "Becoming fine",
    "Fine, becoming less settled",
    "Fine, possible showers",
    "Fairly fine, improving",
    "Fairly fine, possible showers early",
    "Fairly fine, showery later",
    "Showery early, improving",
    "Changeable, mending",
    "Fairly fine, showers likely",
    "Rather unsettled clearing later",
    "Unsettled, probably improving",
    "Showery, bright intervals",
    "Showery, becoming less settled",
    "Changeable, some rain",
    "Unsettled, short fine intervals",
    "Unsettled, rain later",
    "Unsettled, some rain",
    "Mostly very unsettled",
    "Occasional rain, worsening",
    "Rain at times, very unsettled",
    "Rain at frequent intervals",
    "Rain, very unsettled",
    "Stormy, may improve",
    "Stormy, much rain"
)

var rise_options =
    arrayOf(25, 25, 25, 24, 24, 19, 16, 12, 11, 9, 8, 6, 5, 2, 1, 1, 0, 0, 0, 0, 0, 0)
var steady_options =
    arrayOf(25, 25, 25, 25, 25, 25, 23, 23, 22, 18, 15, 13, 10, 4, 1, 1, 0, 0, 0, 0, 0, 0)
var fall_options =
    arrayOf(25, 25, 25, 25, 25, 25, 25, 25, 23, 23, 21, 20, 17, 14, 7, 3, 1, 1, 1, 0, 0, 0)