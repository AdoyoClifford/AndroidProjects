package com.example.calculator

sealed class CalculatorAction{
    data class Number(val number: Int) : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Decimal : CalculatorAction()
    object Result : CalculatorAction()
    data class Operations(val operation: CalculatorOperation) : CalculatorAction()
}
