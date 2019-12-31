package com.meteoship.model.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity
class HourlyWeatherItem {

    @SerializedName("wind_cdir")
    @Expose
    var windCdir: String? = null
    @SerializedName("rh")
    @Expose
    var rh: Int? = null
    @SerializedName("pod")
    @Expose
    var pod: String? = null
    @SerializedName("timestamp_utc")
    @Expose
    var timestampUtc: String? = null
    @SerializedName("pres")
    @Expose
    var pres: Double? = null
    @SerializedName("solar_rad")
    @Expose
    var solarRad: Double? = null
    @SerializedName("ozone")
    @Expose
    var ozone: Double? = null
    @Embedded
    @SerializedName("weather")
    @Expose
    var weather: WeatherMetaData? = null
    @SerializedName("wind_gust_spd")
    @Expose
    var windGustSpd: Double? = null
    @SerializedName("timestamp_local")
    @Expose
    var timestampLocal: String? = null
    @SerializedName("snow_depth")
    @Expose
    var snowDepth: Double? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null
    @SerializedName("ts")
    @Expose
    var ts: Int? = null
    @SerializedName("wind_spd")
    @Expose
    var windSpd: Double? = null
    @SerializedName("pop")
    @Expose
    var pop: Int? = null
    @SerializedName("wind_cdir_full")
    @Expose
    var windCdirFull: String? = null
    @SerializedName("slp")
    @Expose
    var slp: Double? = null
    @SerializedName("dni")
    @Expose
    var dni: Double? = null
    @SerializedName("dewpt")
    @Expose
    var dewpt: Double? = null
    @SerializedName("snow")
    @Expose
    var snow: Double? = null
    @SerializedName("uv")
    @Expose
    var uv: Double? = null
    @SerializedName("wind_dir")
    @Expose
    var windDir: Int? = null
    @SerializedName("clouds_hi")
    @Expose
    var cloudsHi: Int? = null
    @SerializedName("precip")
    @Expose
    var precip: Double? = null
    @SerializedName("vis")
    @Expose
    var vis: Double? = null
    @SerializedName("dhi")
    @Expose
    var dhi: Double? = null
    @SerializedName("app_temp")
    @Expose
    var appTemp: Double? = null
    @SerializedName("datetime")
    @Expose
    var datetime: String? = null
    @SerializedName("temp")
    @Expose
    var temp: Double? = null
    @SerializedName("ghi")
    @Expose
    var ghi: Double? = null
    @SerializedName("clouds_mid")
    @Expose
    var cloudsMid: Int? = null
    @SerializedName("clouds_low")
    @Expose
    var cloudsLow: Int? = null

    var lon: Double? = null

    var lat: Double? = null

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    fun isNight():Boolean{
        return pod == "n"
    }

}