package com.meteoship.model.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity
class DailyWeatherItem {

    @SerializedName("moonrise_ts")
    @Expose
    var moonriseTs: Int? = null //Moonrise time unix timestamp (UTC).
    @SerializedName("wind_cdir")
    @Expose
    var windCdir: String? = null //Abbreviated wind direction.

    @SerializedName("rh")
    @Expose
    var rh: Int? = null //Average relative humidity (%).

    @SerializedName("pres")
    @Expose
    var pres: Double? = null //Average pressure (mb).

    @SerializedName("high_temp")
    @Expose
    var highTemp: Double? =
        null //High Temperature - Calculated from 6AM to 6AM local time (default Celcius).

    @SerializedName("sunset_ts")
    @Expose
    var sunsetTs: Int? = null //Sunset time unix timestamp (UTC) .

    @SerializedName("ozone")
    @Expose
    var ozone: Double? = null //Average Ozone (Dobson units).

    @SerializedName("moon_phase")
    @Expose
    var moonPhase: Double? = null //Moon phase fraction (0-1).

    @SerializedName("wind_gust_spd")
    @Expose
    var windGustSpd: Double? = null //Wind gust speed (Default m/s).

    @SerializedName("snow_depth")
    @Expose
    var snowDepth: Double? = null //Snow Depth (default mm).

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null //Average total cloud coverage (%).

    @SerializedName("ts")
    @Expose
    var ts: Int? = null //Forecast period start unix timestamp (UTC).

    @SerializedName("sunrise_ts")
    @Expose
    var sunriseTs: Int? = null //Sunrise time unix timestamp (UTC).

    @SerializedName("app_min_temp")
    @Expose
    var appMinTemp: Double? =
        null //Apparent/"Feels Like" temperature at min_temp time (default Celcius).

    @SerializedName("wind_spd")
    @Expose
    var windSpd: Double? = null //Wind speed (Default m/s).

    @SerializedName("pop")
    @Expose
    var pop: Int? = null //Probability of Precipitation (%).

    @SerializedName("wind_cdir_full")
    @Expose
    var windCdirFull: String? = null //Verbal wind direction.

    @SerializedName("slp")
    @Expose
    var slp: Double? = null //Average sea level pressure (mb).

    @SerializedName("valid_date")
    @Expose
    var validDate: String? =
        null //Date the forecast is valid for in format YYYY-MM-DD. [Midnight to midnight local time]

    @SerializedName("app_max_temp")
    @Expose
    var appMaxTemp: Double? =
        null //Apparent/"Feels Like" temperature at max_temp time (default Celcius).

    @SerializedName("vis")
    @Expose
    var vis: Double? = null //Visibility (default KM).

    @SerializedName("dewpt")
    @Expose
    var dewpt: Double? = null //Average dew point (default Celcius).

    @SerializedName("snow")
    @Expose
    var snow: Double? = null //Accumulated snowfall (default mm).

    @SerializedName("uv")
    @Expose
    var uv: Double? = null //Maximum UV Index (0-11+).

    @Embedded
    @SerializedName("weather")
    @Expose
    var weather: WeatherMetaData? = null
    @SerializedName("wind_dir")
    @Expose
    var windDir: Int? = null //Wind direction (degrees).

    @SerializedName("max_dhi")
    @Expose
    var maxDhi: String? = null //[DEPRECATED] Maximum direct component of solar radiation (W/m^2).

    @SerializedName("clouds_hi")
    @Expose
    var cloudsHi: Int? = null //High-level (>5km AGL) cloud coverage (%).

    @SerializedName("precip")
    @Expose
    var precip: Double? = null //Accumulated liquid equivalent precipitation (default mm).

    @SerializedName("low_temp")
    @Expose
    var lowTemp: Double? =
        null //Low Temperature - Calculated from 6AM to 6AM local (default Celcius).

    @SerializedName("max_temp")
    @Expose
    var maxTemp: Double? = null //Maximum Temperature (default Celcius).

    @SerializedName("moonset_ts")
    @Expose
    var moonsetTs: Int? = null //Moonset time unix timestamp (UTC).

    @SerializedName("datetime")
    @Expose
    var datetime: String? = null //[DEPRECATED] Forecast valid date (YYYY-MM-DD).
    @SerializedName("temp")
    @Expose
    var temp: Double? = null //Average Temperature (default Celcius).

    @SerializedName("min_temp")
    @Expose
    var minTemp: Double? = null //Minimum Temperature (default Celcius).

    @SerializedName("clouds_mid")
    @Expose
    var cloudsMid: Int? = null //Mid-level (~3-5km AGL) cloud coverage (%).

    @SerializedName("clouds_low")
    @Expose
    var cloudsLow: Int? = null //Low-level (~0-3km AGL) cloud coverage (%).

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

}