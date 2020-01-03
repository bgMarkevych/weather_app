package com.meteoship.neural_network.data

import com.meteoship.neural_network.data.BaroTrend
import com.meteoship.neural_network.data.Month
import com.meteoship.neural_network.data.Wind

// z_hpa is Sea Level Adjusted (Relative) barometer in hPa or mB
// z_month is current month as a number between 1 to 12
// z_wind is English windrose cardinal eg. N, NNW, NW etc.
// NB. if calm a 'nonsense' value should be sent as z_wind (direction) eg. 1 or calm !
// z_trend is barometer trend: 0 = no change, 1= rise, 2 = fall
// z_where - OPTIONAL for posting with form
// z_baro_top - OPTIONAL for posting with form
// z_baro_bottom - OPTIONAL for posting with form
// [0] a short forecast text is returned
// [1] zambretti severity number (0 - 25) is returned ie. betel_cast() returns a two deep array

class Neuron(
    var z_hpa: Double,
    var z_month: Month,
    var z_wind: Wind,
    var z_trend: BaroTrend,
    var z_hemisphere: Int,
    var z_upper: Double,
    var z_lower: Double
)