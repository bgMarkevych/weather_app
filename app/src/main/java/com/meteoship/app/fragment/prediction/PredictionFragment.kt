package com.meteoship.app.fragment.prediction

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.transition.TransitionManager
import com.meteoship.R
import com.meteoship.base.BaseFragment
import com.meteoship.neural_network.data.BaroTrend
import com.meteoship.neural_network.data.Month
import com.meteoship.neural_network.data.Wind
import kotlinx.android.synthetic.main.fragment_prediction.*

class PredictionFragment : BaseFragment(), PredictionView {

    private lateinit var presenter: PredictionPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = PredictionPresenter()
        presenter.attach(this)
        val winds = arrayListOf<String>()
        Wind.values().forEach {
            winds.add(it.title)
        }
        wind.adapter = ArrayAdapter<String>(context!!, R.layout.spinner_item, winds)
        val months = arrayListOf<String>()
        Month.values().forEach {
            months.add(it.title)
        }
        month.adapter = ArrayAdapter<String>(context!!, R.layout.spinner_item, months)
        val trends = arrayListOf<String>()
        BaroTrend.values().forEach {
            trends.add(it.title)
        }
        trend.adapter = ArrayAdapter<String>(context!!, R.layout.spinner_item, trends)
        predict.setOnClickListener {
            presenter.predictWeather(
                current_pressure.text.toString().toDouble(),
                Month.getMonthByTitle(month.selectedItem as String)!!,
                Wind.getWindByTitle(wind.selectedItem as String)!!,
                BaroTrend.getBaroTrendByTitle(trend.selectedItem as String)!!,
                if (hemisphere.checkedRadioButtonId == R.id.south) 0 else 1,
                pressure_high.text.toString().toDouble(),
                pressure_low.text.toString().toDouble()
            )
        }
        pressure_high.addTextChangedListener(predictTextWatcher)
        pressure_low.addTextChangedListener(predictTextWatcher)
        current_pressure.addTextChangedListener(predictTextWatcher)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prediction, container, false)
    }

    val predictTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            predict.isEnabled =
                !pressure_high.text.isNullOrEmpty() && !pressure_low.text.isNullOrEmpty() && !current_pressure.text.isNullOrEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }


    }

    override fun showResult(result: String) {
        TransitionManager.beginDelayedTransition(view as ViewGroup)
        result_container.visibility = View.VISIBLE
        weather_result.text = result
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

}