package com.example.newsamplecalculaterapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.newsamplecalculaterapp.Enum.MathOperations
import com.example.newsamplecalculaterapp.databinding.ActivityMainBinding
import com.example.newsamplecalculaterapp.Helper.OperationHelper
import com.example.newsamplecalculaterapp.model.dataclass.CalculatorData
import com.example.newsamplecalculaterapp.session.SessionPref
import com.example.newsamplecalculaterapp.view.adapter.CalculatorAdapter
import com.example.newsamplecalculaterapp.viewmodel.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.Exception
import java.lang.NumberFormatException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val calculatorViewModel by viewModels<CalculatorViewModel>()

    private val calculatorAdapter by lazy { CalculatorAdapter() }

    private var mathOperation = MathOperations.ADDITION

    private var digitOnScreen = StringBuilder("")

    private var leftHandSide: Double = 0.0

    private var rightHandSide: Double = 0.0

    @Inject
    lateinit var sessionPref: SessionPref

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.resultId.text = "0"
        binding.textSetvalue.text = sessionPref.getCalculatorValue()
        performMathOperation()
        functionalButtons()
        operationalButtons()
        numericalButtons()
    }

    private fun clearDigit() {
        val length = digitOnScreen.length
        digitOnScreen.deleteCharAt(length - 1)
        if (length <= 0) {
            binding.resultId.text = "0"
        } else {
            binding.resultId.text = digitOnScreen.toString()
        }
    }

    private fun numericalButtons() {

        binding.oneBtn.setOnClickListener {
            appendToDigitOnScreen("1")
        }

        binding.twoBtn.setOnClickListener {
            appendToDigitOnScreen("2")
        }

        binding.threeBtn.setOnClickListener {
            appendToDigitOnScreen("3")
        }

        binding.fourBtn.setOnClickListener {
            appendToDigitOnScreen("4")
        }

        binding.fiveBtn.setOnClickListener {
            appendToDigitOnScreen("5")
        }

        binding.sixBtn.setOnClickListener {
            appendToDigitOnScreen("6")
        }

        binding.sevenBtn.setOnClickListener {
            appendToDigitOnScreen("7")
        }

        binding.eightBtn.setOnClickListener {
            appendToDigitOnScreen("8")
        }

        binding.nineBtn.setOnClickListener {
            appendToDigitOnScreen("9")
        }

        binding.zeroBtn.setOnClickListener {
            appendToDigitOnScreen("0")
        }

        binding.dotBtn.setOnClickListener {
            appendToDigitOnScreen(".")
        }

    }


    private fun appendToDigitOnScreen(digit: String) {


        digitOnScreen.append(digit)

        binding.resultId.text = digitOnScreen.toString()
    }


    private fun operationalButtons() {

        binding.additionBtn.setOnClickListener {
            selectOperation(MathOperations.ADDITION)
        }

        binding.subtractBtn.setOnClickListener {
            selectOperation(MathOperations.SUBTRACTION)
        }

        binding.divideBtn.setOnClickListener {
            selectOperation(MathOperations.DIVISION)
        }

        binding.multipyBtn.setOnClickListener {
            selectOperation(MathOperations.MULTIPLICATION)
        }

    }

    private fun selectOperation(mathOperations: MathOperations) {

        try {
            this.mathOperation = mathOperations
            leftHandSide = digitOnScreen.toString().toDouble()
            digitOnScreen.clear()
            binding.resultId.text = "0"
        } catch (n: NumberFormatException) {

        }

    }

    private fun functionalButtons() {

        binding.clearEverythingBtn.setOnClickListener {
            digitOnScreen.clear()
            binding.resultId.text = "0"
            binding.textSetvalue.text ="0"
        }

        binding.clearBtn.setOnClickListener {

            if (digitOnScreen.isEmpty()) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        binding.backspaceBtn.setOnClickListener {
            if (digitOnScreen.isEmpty()) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        binding.equalBtn.setOnClickListener {
            try {

                val itemName = binding.textSetvalue.text.toString()

                val item = CalculatorData(
                    result = itemName
                )

                calculatorViewModel.insertData(item)

                performMathOperation()
            } catch (e: Exception) {
                Timber.d("Exceptions are : $e")
            }

        }

    }

    private fun performMathOperation() {

        try {
            rightHandSide = digitOnScreen.toString().toDouble()
            when (mathOperation) {
                MathOperations.ADDITION -> {
                    val sum = OperationHelper.add(leftHandSide, rightHandSide)
                    binding.textSetvalue.text = sum.toString()
                    sessionPref.setCalculateTheValue(sum.toString())

                    digitOnScreen.clear()
                    digitOnScreen.append(sum)
                }

                MathOperations.SUBTRACTION -> {
                    val subtract = OperationHelper.subtract(leftHandSide, rightHandSide)
                    binding.textSetvalue.text = subtract.toString()
                    sessionPref.setCalculateTheValue(subtract.toString())

                    digitOnScreen.clear()
                    digitOnScreen.append(subtract)
                }

                MathOperations.MULTIPLICATION -> {
                    val multiply = OperationHelper.multiply(leftHandSide, rightHandSide)
                    binding.textSetvalue.text = multiply.toString()
                    sessionPref.setCalculateTheValue(multiply.toString())
                    digitOnScreen.clear()
                    digitOnScreen.append(multiply)
                }

                MathOperations.DIVISION -> {
                    val divide = OperationHelper.divide(leftHandSide, rightHandSide)
                    binding.textSetvalue.text = divide.toString()
                    sessionPref.setCalculateTheValue(divide.toString())
                    digitOnScreen.clear()
                    digitOnScreen.append(divide)
                }
            }
        } catch (n: NumberFormatException) {

        }
    }

}

