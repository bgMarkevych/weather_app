package com.meteoship.model.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class CurrentWeatherItem {

    companion object {
        val dateFormat = "yyyy-MM-dd HH:mm"
    }

    @SerializedName("rh")
    @Expose
    var rh: Int? = null  //Relative humidity (%).
    @SerializedName("pod")
    @Expose
    var pod: String? = null //Part of the day (d = day / n = night).
    @SerializedName("lon")
    @Expose
    var lon: Double? = null //Longitude (Degrees).
    @SerializedName("pres")
    @Expose
    var pres: Double? = null //Pressure (mb).
    @SerializedName("timezone")
    @Expose
    var timezone: String? = null //Local IANA Timezone.
    @SerializedName("ob_time")
    @Expose
    var obTime: String? = null //Last observation time (YYYY-MM-DD HH:MM).
    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null //Cloud coverage (%).
    @SerializedName("ts")
    @Expose
    var ts: Int? = null //Last observation time (Unix timestamp).
    @SerializedName("solar_rad")
    @Expose
    var solarRad: Double? = null //Estimated Solar Radiation (W/m^2).
    @SerializedName("state_code")
    @Expose
    var stateCode: String? = null //State abbreviation/code.
    @SerializedName("city_name")
    @Expose
    var cityName: String? = null
    @SerializedName("wind_spd")
    @Expose
    var windSpd: Double? = null //Wind speed (Default m/s).
    @SerializedName("last_ob_time")
    @Expose
    var lastObTime: String? = null
    @SerializedName("wind_cdir_full")
    @Expose
    var windCdirFull: String? = null //Verbal wind direction.
    @SerializedName("wind_cdir")
    @Expose
    var windCdir: String? = null //Abbreviated wind direction.
    @SerializedName("slp")
    @Expose
    var slp: Double? = null //Sea level pressure (mb).
    @SerializedName("vis")
    @Expose
    var vis: Double? = null //Visibility (default KM).
    @SerializedName("h_angle")
    @Expose
    var hAngle: Double? = null //Solar hour angle (degrees).
    @SerializedName("sunset")
    @Expose
    var sunset: String? = null
    @SerializedName("dni")
    @Expose
    var dni: Double? = null //Direct normal solar irradiance (W/m^2) [Clear Sky]
    @SerializedName("dewpt")
    @Expose
    var dewpt: Double? = null //Dew point (default Celcius).
    @SerializedName("snow")
    @Expose
    var snow: Double? = null //Snowfall (default mm/hr).
    @SerializedName("uv")
    @Expose
    var uv: Double? = null //UV Index (0-11+).
    @SerializedName("precip")
    @Expose
    var precip: Int? = null //Liquid equivalent precipitation rate (default mm/hr).
    @SerializedName("wind_dir")
    @Expose
    var windDir: Int? = null //Wind direction (degrees).
    @SerializedName("sunrise")
    @Expose
    var sunrise: String? = null
    @SerializedName("ghi")
    @Expose
    var ghi: Double? = null //Global horizontal solar irradiance (W/m^2) [Clear Sky]
    @SerializedName("dhi")
    @Expose
    var dhi: Double? = null //Diffuse horizontal solar irradiance (W/m^2) [Clear Sky]
    @SerializedName("aqi")
    @Expose
    var aqi: Int? = null //Air Quality Index [US - EPA standard 0 - +500]
    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    @Embedded
    @SerializedName("weather")
    @Expose
    var weather: WeatherMetaData? = null
    @SerializedName("datetime")
    @Expose
    var datetime: String? = null //Current cycle hour (YYYY-MM-DD:HH).
    @SerializedName("temp")
    @Expose
    var temp: Double? = null //Temperature (default Celcius).
    @SerializedName("station")
    @Expose
    var station: String? = null //Source station ID.
    @SerializedName("elev_angle")
    @Expose
    var elevAngle: Double? = null //Solar elevation angle (degrees).
    @SerializedName("app_temp")
    @Expose
    var appTemp: Double? = null //Apparent/"Feels Like" temperature (default Celcius).
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    fun isNight(): Boolean {
        return pod == "n"
    }


}