package com.example.relegraphapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.relegraphapp.databinding.ActivityMainBinding
import com.example.relegraphapp.graph.Graph
import com.jjoe64.graphview.GraphView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var graph: GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        onTouchCloseKeyboard()

        binding.apply {

            graph = graphView

            graphButton.setOnClickListener {
                Graph(graphView).initGraph()
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