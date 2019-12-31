package com.meteoship.model.data

import androidx.annotation.StringRes
import androidx.room.Entity
import com.meteoship.R

@Entity
class ForecastInfoDataItem(@StringRes val name: Int, val value: String, val colorInt: Int = R.color.default_text_color)