package com.macode.a10minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import com.macode.a10minuteworkout.databinding.ActivityBMIBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBMIBinding
    val metric = "metricView"
    val us = "usView"

    var currentView: String = metric

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBMIBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = findViewById<Toolbar>(R.id.bmiToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Calculate BMI"

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.calculateBMIButton.setOnClickListener {
            if (currentView == metric) {
                if (binding.metricWeightEditInput.text.toString().isEmpty()) {
                    showError(binding.metricWeightInput, "Please enter your weight!")
                } else if (binding.metricHeightEditInput.text.toString().isEmpty()) {
                    showError(binding.metricHeightInput, "Please enter your height!")
                } else {
                    val weightValue: Float = binding.metricWeightEditInput.text.toString().toFloat()
                    val heightValue: Float = binding.metricHeightEditInput.text.toString().toFloat() / 100
                    val metricBMI = weightValue / (heightValue*heightValue)
                    displayBMIResult(metricBMI)
                }
            } else {
                if (binding.usWeightEditInput.text.toString().isEmpty()) {
                    showError(binding.usWeightInput, "Please enter your weight!")
                } else if (binding.usHeightFeetEditInput.text.toString().isEmpty()) {
                    showError(binding.usHeightFeetInput, "Please enter height in feet!")
                } else if (binding.usHeightInchesEditInput.text.toString().isEmpty()) {
                    showError(binding.usHeightInchesInput, "Please enter height in inches!")
                } else {
                    val usHeightFeet: String = binding.usHeightFeetEditInput.text.toString()
                    val usHeightInches: String = binding.usHeightInchesEditInput.text.toString()
                    val usWeight: Float = binding.usWeightEditInput.text.toString().toFloat()
                    val usHeight: Float = usHeightInches.toFloat() + usHeightFeet.toFloat() * 12
                    val usBMI = 703 * (usWeight / (usHeight*usHeight))
                    println(usBMI)
                    displayBMIResult(usBMI)
                }
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.radioButtonMetric) {
                binding.metricWeightEditInput.text!!.clear()
                binding.metricHeightEditInput.text!!.clear()
                binding.metricBMICalculationResultLinear.visibility = View.GONE
                binding.usBMICalculationResultLinear.visibility = View.GONE
                binding.metricRelative.visibility = View.VISIBLE
                binding.usRelative.visibility = View.GONE
                currentView = metric
            } else if (checkedId == R.id.radioButtonUS){
                binding.usWeightEditInput.text!!.clear()
                binding.usHeightFeetEditInput.text!!.clear()
                binding.usHeightInchesEditInput.text!!.clear()
                binding.metricBMICalculationResultLinear.visibility = View.GONE
                binding.usBMICalculationResultLinear.visibility = View.GONE
                binding.usRelative.visibility = View.VISIBLE
                binding.metricRelative.visibility = View.INVISIBLE
                currentView = us
            }
        }
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Whoa! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Whoa! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Whoa! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Whoa! You really need to take better care of yourself! Workout more!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese class I | Moderately obese"
            bmiDescription = "Whoa! You really need to take better care of yourself! Workout more!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese class II | Severely obese"
            bmiDescription = "OMG! You really need to take better care of yourself! Put the cookie down and workout ASAP!"
        } else {
            bmiLabel = "Obese class III | Very severely obese"
            bmiDescription = "You are so fat, when you fell I didn't laugh, but the sidewalk cracked up."
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        if (currentView == metric) {
            binding.metricBMICalculationResultLinear.visibility = View.VISIBLE

            binding.metricBMIResult.text = bmiValue
            "$bmiLabel\n$bmiDescription".also { binding.metricBMIResultDescription.text = it }
        } else {
            binding.usBMICalculationResultLinear.visibility = View.VISIBLE

            binding.usBMIResult.text = bmiValue
            "$bmiLabel\n$bmiDescription".also { binding.usBMIResultDescription.text = it }
        }
    }

    private fun showError(layout: TextInputLayout, text: String) {
        layout.error = text
        layout.requestFocus()
    }
}