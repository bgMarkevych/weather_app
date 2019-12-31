package com.meteoship.utils

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.meteoship.R
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class WeatherDateView : View {
    constructor(context: Context) : this(context, null) {
        init(null)
        initTools()
    }

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0) {
        init(attributes)
        initTools()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
        initTools()
    }

    private var textPadding = 0f
    var day: String? = null
        set(value) {
            field = value
            invalidate()
        }
    var month: String? = null
        set(value) {
            field = value
            invalidate()
        }
    var year: String? = null
        set(value) {
            field = value
            invalidate()
        }
    var color = 0
        set(value) {
            field = value
            invalidate()
        }
    var dayTextSize = 0f
        set(value) {
            field = value
            invalidate()
        }
    var monthTextSize = 0f
        set(value) {
            field = value
            invalidate()
        }
    var yearTextSize = 0f
        set(value) {
            field = value
            invalidate()
        }
    private lateinit var textPaint: Paint
    private lateinit var dayRect: Rect
    private lateinit var monthRect: Rect
    private lateinit var yearRect: Rect
    private val monthFormat = SimpleDateFormat("MMMM", Locale.US)

    private fun initTools() {
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.color = color
        textPaint.style = Paint.Style.FILL
        textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        dayRect = Rect()
        monthRect = Rect()
        yearRect = Rect()
    }

    private fun init(attrs: AttributeSet?) {
        initDefaultSources()
        textPadding = context.resources.getDimension(R.dimen.padding_date_view_text)
        if (attrs == null) {
            return
        }
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeatherDateView)
        try {
            day = typedArray.getString(R.styleable.WeatherDateView_day)
            month = typedArray.getString(R.styleable.WeatherDateView_month)
            year = typedArray.getString(R.styleable.WeatherDateView_year)
            color = typedArray.getColor(
                R.styleable.WeatherDateView_color,
                ContextCompat.getColor(context, android.R.color.white)
            )
            dayTextSize = typedArray.getDimension(
                R.styleable.WeatherDateView_dayTextSize,
                context.resources.getDimension(R.dimen.default_text_day_size)
            )
            monthTextSize = typedArray.getDimension(
                R.styleable.WeatherDateView_monthTextSize,
                context.resources.getDimension(R.dimen.default_text_month_size)
            )
            yearTextSize = typedArray.getDimension(
                R.styleable.WeatherDateView_yearTextSize,
                context.resources.getDimension(R.dimen.default_text_year_size)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            initDefaultSources()
        }
        if (day.isNullOrEmpty()) {
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        }
        if (month.isNullOrEmpty()) {
            month = monthFormat.format(Date())
        }
        if (year.isNullOrEmpty()) {
            year = Calendar.getInstance().get(Calendar.YEAR).toString()
        }
        typedArray.recycle()
    }

    private fun initDefaultSources() {
        val calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH).toString()
        month = monthFormat.format(Date())
        year = calendar.get(Calendar.YEAR).toString()
        color = Color.WHITE
        dayTextSize = context.resources.getDimension(R.dimen.default_text_day_size)
        monthTextSize = context.resources.getDimension(R.dimen.default_text_month_size)
        yearTextSize = context.resources.getDimension(R.dimen.default_text_year_size)
    }

    override fun onDraw(canvas: Canvas?) {
        textPaint.textSize = dayTextSize
        canvas?.drawText(
            day!!,
            0f,
            yearRect.height().toFloat() + monthRect.height().toFloat() + textPadding,
            textPaint
        )
        textPaint.textSize = monthTextSize
        canvas?.drawText(
            month!!,
            dayRect.width() + textPadding,
            yearRect.height().toFloat() + monthRect.height().toFloat() + textPadding,
            textPaint
        )
        textPaint.textSize = yearTextSize
        canvas?.drawText(
            year!!,
            dayRect.width() + textPadding,
            yearRect.height().toFloat(),
            textPaint
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        textPaint.textSize = dayTextSize
        textPaint.getTextBounds(day, 0, day!!.length, dayRect)
        textPaint.textSize = monthTextSize
        textPaint.getTextBounds(month, 0, month!!.length, monthRect)
        textPaint.textSize = yearTextSize
        textPaint.getTextBounds(year, 0, year!!.length, yearRect)
        var width = dayRect.width()
        var height = textPadding
        height += if (dayRect.height() > yearRect.height() + monthRect.height()) {
            dayRect.height()
        } else {
            yearRect.height() + monthRect.height()
        }
        width += if (monthRect.width() > yearRect.width()) {
            monthRect.width()
        } else {
            yearRect.width()
        }
        width += textPadding.toInt()
        setMeasuredDimension(width, height.toInt())
    }

    fun setDate(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date.time
        day = calendar.get(Calendar.DAY_OF_MONTH).toString()
        month = monthFormat.format(date)
        year = calendar.get(Calendar.YEAR).toString()
    }

    fun setDate(date: String, dateFormat: String) {
        if (date.contains("T")){
            date.replace("T", "")
        }
        val format = SimpleDateFormat(dateFormat, Locale.US)
        try {
            val dateObject = format.parse(date)
            setDate(dateObject)
        } catch (e: Exception) {
            e.printStackTrace()
            initDefaultSources()
        }
    }

}