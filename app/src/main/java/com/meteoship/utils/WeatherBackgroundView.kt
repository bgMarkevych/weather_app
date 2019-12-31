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
import android.animation.ValueAnimator
import androidx.core.animation.addListener
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator


const val OVAL_HEIGHT_PERCENT = 70
const val COLOR_PREF_NAME = "COLOR_PREF_NAME"
const val COLOR_PREF_KEY = "COLOR_PREF_KEY"

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
            saveColor(color)
            return
        }
        val typedArray =
            context.obtainStyledAttributes(attributes, R.styleable.WeatherBackgroundView)
        try {
            color = typedArray.getColor(
                R.styleable.WeatherBackgroundView_backgroundColor,
                ContextCompat.getColor(context, R.color.sunny)
            )
            if (getSavedColor() == 0) {
                saveColor(color)
            } else {
                color = getSavedColor()
            }
            sheepBitmap = getBitmapFromVectorDrawable(
                typedArray.getResourceId(
                    R.styleable.WeatherBackgroundView_sheepDrawable,
                    0
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            color = ContextCompat.getColor(context, R.color.sunny)
            saveColor(color)
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
        if (sheepBitmap != null) {
            canvas?.drawBitmap(
                sheepBitmap!!,
                width - sheepBitmap!!.width - padding,
                (height - ovalHeight - sheepBitmap!!.height / 2).toFloat(),
                null
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        ovalHeight = MeasureSpec.getSize(heightMeasureSpec) * OVAL_HEIGHT_PERCENT / 100
    }

    private fun getBitmapFromVectorDrawable(drawableId: Int): Bitmap? {
        if (drawableId == 0) {
            return null
        }
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

    fun setImageResource(res: Int) {
        sheepBitmap = getBitmapFromVectorDrawable(res)
        invalidate()
    }

    fun setColorBackground(color: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), this.color, color)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            this.color = (animator.animatedValue as Int)
            invalidate()
        }
        colorAnimation.addListener(onEnd = { saveColor(color) })
        colorAnimation.start()
    }

    private fun saveColor(color: Int) {
        val preferences = context.getSharedPreferences(COLOR_PREF_NAME, Context.MODE_PRIVATE)
        preferences.edit().putInt(COLOR_PREF_KEY, color).apply()
    }

    private fun getSavedColor(): Int {
        val preferences = context.getSharedPreferences(COLOR_PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getInt(COLOR_PREF_KEY, 0)
    }

}