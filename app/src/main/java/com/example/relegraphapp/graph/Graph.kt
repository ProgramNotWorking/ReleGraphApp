package com.example.relegraphapp.graph

import android.graphics.Color
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.lang.Math.pow
import kotlin.math.*

class Graph(
    private val graphView: GraphView, val w: Int
) {

    companion object {
        private val wSeries = LineGraphSeries<DataPoint>().apply {
            title = "w(t)"
            color = Color.RED
        }
        private val lSeries = LineGraphSeries<DataPoint>().apply {
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
        private val mainSeries = LineGraphSeries<DataPoint>().apply {
            title = "t"
            color = Color.YELLOW
        }

        private val a = -1.0
        private val b = 2.996
        // private val l = 1.0
        private val K = 1000000.0
        private val T = 40.0
    }

    private fun calculateW(t: Double, operation: (Double) -> Double): Double {
        return K * max(operation(t) - b, 0.0) * (1 - w) - K * max(a - operation(t), 0.0)
    }

    private fun x(t: Double) = 2 * sin(t)

    fun getWValue() = calculateW(w.toDouble()) { 3 * sin(it) }

    fun initGraph() {
        calculateW(1.0) { 3 * sin(it) }

        for (t in 0..(T * 10).toInt() step 1) {
            // val w = calculateW(t.toDouble(), K, a, b, l, w0)
            val w = if (x((t / 10).toDouble()) >= 0) {
                1.0
            } else {
                0.0
            }

            mainSeries.appendData(DataPoint((t / 10).toDouble(), 0.0), true, 1000)
             wSeries.appendData(DataPoint((t / 10).toDouble(), w), true, 1000)
             lSeries.appendData(DataPoint((t / 10).toDouble(), b), true, 1000)
             aSeries.appendData(DataPoint((t / 10).toDouble(), a), true, 1000)
             xSeries.appendData(DataPoint((t / 10).toDouble(), x((t / 10).toDouble())), true, 1000)
        }

        graphView.addSeries(mainSeries)
        graphView.addSeries(wSeries)
        graphView.addSeries(lSeries)
        graphView.addSeries(aSeries)
        graphView.addSeries(xSeries)

        graphView.legendRenderer.isVisible = true
        graphView.legendRenderer.align = LegendRenderer.LegendAlign.BOTTOM
    }

}