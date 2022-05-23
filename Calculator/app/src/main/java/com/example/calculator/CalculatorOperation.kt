package com.example.calculator

sealed class CalculatorOperation(val operation: String) {
    class Add : CalculatorOperation("+")
    class Subtract : CalculatorOperation("-")
    class Multiply : CalculatorOperation("*")
    class Divide : CalculatorOperation("/")
}
