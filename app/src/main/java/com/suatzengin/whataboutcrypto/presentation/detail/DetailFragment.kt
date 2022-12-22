package com.suatzengin.whataboutcrypto.presentation.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.suatzengin.whataboutcrypto.R
import com.suatzengin.whataboutcrypto.databinding.FragmentDetailBinding
import com.suatzengin.whataboutcrypto.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.hide()
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllData(args.coin.id)
        observeData()
        buttonClickListener()
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    val chartData = getData(state.priceList)
                    displayLineChart(chartData)
                    binding.text.text = state.timeRange.value.toString()

                }
            }
        }
    }

    private fun buttonClickListener(id: String = args.coin.id) {
        binding.oneDay.setOnClickListener { viewModel.setCoinChartTimeSpan(1, id) }
        binding.sevenDay.setOnClickListener { viewModel.setCoinChartTimeSpan(7, id) }
        binding.fourteenDay.setOnClickListener { viewModel.setCoinChartTimeSpan(14, id) }
        binding.thirtyDay.setOnClickListener { viewModel.setCoinChartTimeSpan(30, id) }
        binding.sixtyDay.setOnClickListener { viewModel.setCoinChartTimeSpan(60, id) }
    }

    private fun displayLineChart(chartData: Pair<List<String>, List<Entry>>) {
        binding.lineChart.apply {
            val lineDataSet = LineDataSet(chartData.second, "Value")
            lineDataSet.setDrawFilled(true)
            val formatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return if (value.toInt() < chartData.first.size) chartData.first[value.toInt()] else ""
                }
            }
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            xAxis.valueFormatter = formatter
            xAxis.labelRotationAngle = 90f

            lineDataSet.setDrawCircles(false)
            lineDataSet.disableDashedLine()
            lineDataSet.setDrawValues(false)
            description.text = "Usd"
            val data = LineData(lineDataSet)
            this.data = data
            lineDataSet.fillDrawable = lineFillDrawable()
            setTouchEnabled(true)
            setPinchZoom(true)
            invalidate()

            animateX(1500, Easing.EaseInExpo)
        }
    }

    private fun lineFillDrawable(): Drawable? {
        return if (args.coin.priceChangePercentage24h > 0) {
            ContextCompat.getDrawable(requireContext(), R.drawable.chart_bg_increase)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.chart_bg_decrease)
        }
    }

    private fun getData(list: List<List<Double>>): Pair<List<String>, List<Entry>> {
        val xAxisValues = arrayListOf<String>()
        val entries = arrayListOf<Entry>()

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)

        list.forEachIndexed { index, entry ->
            val date = Date(entry[0].toLong())
            val label = simpleDateFormat.format(date)
            xAxisValues.add(label)
            entries.add(Entry(index.toFloat(), entry[1].toFloat()))
        }

        return Pair(xAxisValues, entries)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}