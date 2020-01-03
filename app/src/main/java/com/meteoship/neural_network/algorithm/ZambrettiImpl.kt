package com.meteoship.neural_network.algorithm

import com.meteoship.neural_network.data.*
import kotlin.math.floor

class ZambrettiImpl : Zambretti {

    override fun doTask(neuron: Neuron): String {
        var zRange = neuron.z_upper - neuron.z_lower
        var zConstant = zRange / 22
        var zHpa = neuron.z_hpa

        var zSeason =
            (neuron.z_month.order in 4..9)
        if (neuron.z_hemisphere == 1) {
            when {
                neuron.z_wind == Wind.N -> zHpa += 6 / 100 * zRange
                neuron.z_wind == Wind.NNE -> zHpa += 5 / 100 * zRange
                neuron.z_wind == Wind.NE -> //			z_hpa += 4 ;
                    zHpa += 5 / 100 * zRange
                neuron.z_wind == Wind.ENE -> zHpa += 2 / 100 * zRange
                neuron.z_wind == Wind.E -> zHpa -= 0.5 / 100 * zRange
                neuron.z_wind == Wind.ESE -> //			z_hpa -= 3 ;
                    zHpa -= 2 / 100 * zRange
                neuron.z_wind == Wind.SE -> zHpa -= 5 / 100 * zRange
                neuron.z_wind == Wind.SSE -> zHpa -= 8.5 / 100 * zRange
                neuron.z_wind == Wind.S -> //			z_hpa -= 11 ;
                    zHpa -= 12 / 100 * zRange
                neuron.z_wind == Wind.SSW -> zHpa -= 10 / 100 * zRange  //
                neuron.z_wind == Wind.SW -> zHpa -= 6 / 100 * zRange
                neuron.z_wind == Wind.WSW -> zHpa -= 4.5 / 100 * zRange  //
                neuron.z_wind == Wind.W -> zHpa -= 3 / 100 * zRange
                neuron.z_wind == Wind.WNW -> zHpa -= 0.5 / 100 * zRange
                neuron.z_wind == Wind.NW -> zHpa += 1.5 / 100 * zRange
                neuron.z_wind == Wind.NNW -> zHpa += 3 / 100 * zRange
            }
            if (zSeason) {    // if Summer
                if (neuron.z_trend == BaroTrend.RAISE) {    // rising
                    zHpa += 7 / 100 * zRange
                } else if (neuron.z_trend == BaroTrend.FAILING) {  //	falling
                    zHpa -= 7 / 100 * zRange
                }
            }
        } else {
            when {
                neuron.z_wind == Wind.S -> zHpa += 6 / 100 * zRange
                neuron.z_wind == Wind.SSW -> zHpa += 5 / 100 * zRange
                neuron.z_wind == Wind.SW -> //			z_hpa += 4 ;
                    zHpa += 5 / 100 * zRange
                neuron.z_wind == Wind.WSW -> zHpa += 2 / 100 * zRange
                neuron.z_wind == Wind.W -> zHpa -= 0.5 / 100 * zRange
                neuron.z_wind == Wind.WNW -> //			z_hpa -= 3 ;
                    zHpa -= 2 / 100 * zRange
                neuron.z_wind == Wind.NW -> zHpa -= 5 / 100 * zRange
                neuron.z_wind == Wind.NNW -> zHpa -= 8.5 / 100 * zRange
                neuron.z_wind == Wind.N -> //			z_hpa -= 11 ;
                    zHpa -= 12 / 100 * zRange
                neuron.z_wind == Wind.NNE -> zHpa -= 10 / 100 * zRange  //
                neuron.z_wind == Wind.NE -> zHpa -= 6 / 100 * zRange
                neuron.z_wind == Wind.ENE -> zHpa -= 4.5 / 100 * zRange  //
                neuron.z_wind == Wind.E -> zHpa -= 3 / 100 * zRange
                neuron.z_wind == Wind.ESE -> zHpa -= 0.5 / 100 * zRange
                neuron.z_wind == Wind.SE -> zHpa += 1.5 / 100 * zRange
                neuron.z_wind == Wind.SSE -> zHpa += 3 / 100 * zRange
            }
            if (!zSeason) {    // if Winter
                if (neuron.z_trend == BaroTrend.RAISE) {  // rising
                    zHpa += 7 / 100 * zRange;
                } else if (neuron.z_trend == BaroTrend.FAILING) {  // falling
                    zHpa -= 7 / 100 * zRange;
                }
            }
        }    // END North / South

        if (zHpa == neuron.z_upper) zHpa = neuron.z_upper - 1;
        var zOption = floor((zHpa - neuron.z_lower) / zConstant).toInt()
        var zOutput = "";
        if (zOption < 0) {
            zOption = 0
            zOutput = "Exceptional Weather, ";
        }
        if (zOption > 21) {
            zOption = 21
            zOutput = "Exceptional Weather, ";
        }

        zOutput += if (neuron.z_trend == BaroTrend.RAISE) {    // rising
            z_forecast[rise_options[zOption]];
        } else if (neuron.z_trend == BaroTrend.FAILING) {    // falling
            z_forecast[fall_options[zOption]];
        } else {    // must be 'steady'
            z_forecast[steady_options[zOption]];
        }
        return zOutput
    }

}