package com.example.relegraphapp.graph

import android.graphics.Color
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlin.math.*

class Graph(private val graphView: GraphView) {

    companion object {
        private val wSeries = LineGraphSeries<DataPoint>().apply {
            title = "w(t)"
            color = Color.RED
        }
        private val bSeries = LineGraphSeries<DataPoint>().apply {
            title = "b"
            color = Color.WHITE
        }
        private val aSeries = LineGraphSeries<DataPoint>().apply {
            title = "a"
            color = Color.GREEN
        }
        private val xSeries = LineGraphSeries<DataPoint>().apply {
            title = "x"
            color = Color.CYAN
        }

        private val a = -1.0
        private val l = 1.0
        private val b = 2.996
        private val w0 = 0.0
        private val K = 1000000.0
        private val T = 40.0
    }

    private fun x(t: Double) = 3 * sin(t)

    private fun calculateW(
        t: Double,
        K: Double,
        a: Double,
        b: Double,
        l: Double,
        w0: Double): Double {
        // Реализация вашего дифференциального уравнения здесь
        // Это пример, вам нужно будет адаптировать его под вашу конкретную логику
        val x = 3 * sin(t)
        val w = w0 + K * max(x - b, 0.0) * (1 - w0) - K * max(a - x, 0.0) * w0
        return w
    }

    fun initGraph() {
        for (t in 0..(T * 10).toInt() step 1) {
            // val w = calculateW(t.toDouble(), K, a, b, l, w0)
            val w = w0
            wSeries.appendData(DataPoint((t / 10).toDouble(), w), true, 1000)
            bSeries.appendData(DataPoint((t / 10).toDouble(), w), true, 1000)
            aSeries.appendData(DataPoint((t / 10).toDouble(), w), true, 1000)
            xSeries.appendData(DataPoint((t / 10).toDouble(), x((t / 10).toDouble())), true, 1000)
        }

        graphView.addSeries(wSeries)
        graphView.addSeries(bSeries)
        graphView.addSeries(aSeries)
        graphView.addSeries(xSeries)

        graphView.legendRenderer.isVisible = true
        graphView.legendRenderer.align = LegendRenderer.LegendAlign.BOTTOM
    }

}