package com.meteoship.utils

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.meteoship.R
import java.lang.Exception
import android.graphics.Bitmap
import androidx.core.graphics.drawable.DrawableCompat
import android.os.Build


const val OVAL_HEIGHT_PERCENT = 70

class WeatherBackgroundView : View {
    constructor(context: Context) : this(context, null) {
        init(null)
    }

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0) {
        init(attributes)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private var ovalHeight = 0
    private var padding = 0f
    private lateinit var ovalPaint: Paint

    var color = 0
    var sheepBitmap: Bitmap? = null

    private fun init(attributes: AttributeSet?) {
        if (attributes == null) {
            color = ContextCompat.getColor(context, R.color.sunny)
            sheepBitmap = getBitmapFromVectorDrawable(R.drawable.ic_splash_logo)
            return
        }
        val typedArray =
            context.obtainStyledAttributes(attributes, R.styleable.WeatherBackgroundView)
        try {
            color = typedArray.getColor(
                R.styleable.WeatherBackgroundView_backgroundColor,
                ContextCompat.getColor(context, R.color.sunny)
            )
            sheepBitmap = getBitmapFromVectorDrawable(
                typedArray.getResourceId(
                    R.styleable.WeatherBackgroundView_sheepDrawable,
                    R.drawable.ic_splash_logo
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            color = ContextCompat.getColor(context, R.color.sunny)
            sheepBitmap = getBitmapFromVectorDrawable(R.drawable.ic_splash_logo)
        }
        typedArray.recycle()
        padding = context.resources.getDimension(R.dimen.padding)
        ovalPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        ovalPaint.color = Color.WHITE
        ovalPaint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(color)
        canvas?.drawCircle(
            (width / 1.7).toFloat(),
            (ovalHeight + (ovalHeight / 2)).toFloat(),
            ovalHeight.toFloat(),
            ovalPaint
        )
        canvas?.drawBitmap(
            sheepBitmap!!,
            width - sheepBitmap!!.width - padding,
            (height - ovalHeight - sheepBitmap!!.height / 2).toFloat(),
            null
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        ovalHeight = MeasureSpec.getSize(heightMeasureSpec) * OVAL_HEIGHT_PERCENT / 100
    }

    private fun getBitmapFromVectorDrawable(drawableId: Int): Bitmap {
        var drawable = ContextCompat.getDrawable(context, drawableId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable!!).mutate()
        }

        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    fun setImageResource(res: Int){
        sheepBitmap = getBitmapFromVectorDrawable(res)
        invalidate()
    }

}