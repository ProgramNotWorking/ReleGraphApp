package com.example.relegraphapp.viewmodels

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var inputValue: Int? = null
    private var outputValue: Int? = null
    private var thresholdValue: Int? = null

    fun setInputValue(value: Int) {
        inputValue = value
    }

    fun getInputValue() = inputValue

    fun setOutputValue(value: Int) {
        outputValue = value
    }

    fun getOutputValue() = outputValue

    fun setThresholdValue(value: Int) {
        thresholdValue = value
    }

    fun getThresholdValue() = thresholdValue

}