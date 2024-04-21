package com.example.relegraphapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.relegraphapp.databinding.ActivityMainBinding
import com.example.relegraphapp.graph.Graph

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        onTouchCloseKeyboard()

        binding.apply {

            graphButton.setOnClickListener {
                graphView.removeAllSeries()
                if (
                    wPlainTextView.text.toString() == "" ||
                    aPlainTextView.text.toString() == "" ||
                    bPlainTextView.text.toString() == "" ||
                    KPlainTextView.text.toString() == ""
                    ) {

                    Toast.makeText(
                        this@MainActivity,
                        "Поля ввода должны быть заполнены",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if ("," in bPlainTextView.text.toString()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Записывайте десятичные части не через ',' а через '.'",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val w = wPlainTextView.text.toString().toInt()
                    if ((w >= 0) and (w <= 1)) {
                        val graph = Graph(
                            graphView,
                            w,
                            aPlainTextView.text.toString().toDouble(),
                            bPlainTextView.text.toString().toDouble(),
                            KPlainTextView.text.toString().toDouble()
                        )
                        graph.drawGraph()
                        wResultTextView.text = wResultTextView.text.toString() + graph.getWValue().toString()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            R.string.w_value_is_not_correct,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchCloseKeyboard() {
        binding.graphView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(binding.graphView.windowToken, 0)
            }

            true
        }
    }

}