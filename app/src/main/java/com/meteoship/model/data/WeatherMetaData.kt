package com.meteoship.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

class WeatherMetaData {

    @Expose
    var icon: String? = null

    @Expose
    var code: String? = null

    @Expose
    var description: String? = null

}