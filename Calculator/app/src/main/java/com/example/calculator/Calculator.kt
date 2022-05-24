package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.Orange

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    spacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
){
    Box(modifier = Modifier) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(spacing)) {
            Text(text = state.number1 + (state.operation?.symbol?: "") + state.number2,
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Light,
                maxLines = 2
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                CalculatorButton(symbol = "AC", modifier = modifier
                    .aspectRatio(2f)
                    .weight(2f)
                    .background(LightGray),
                onClick = {
                    onAction(CalculatorAction.Clear)
                })

                CalculatorButton(symbol = "Del", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(LightGray),
                    onClick = {
                        onAction(CalculatorAction.Delete)
                    })

                CalculatorButton(symbol = "/", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Orange),
                    onClick = {
                        onAction(CalculatorAction.Operations(CalculatorOperation.Divide))
                    })
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                CalculatorButton(symbol = "7", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(7))
                    })

                CalculatorButton(symbol = "8", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(8))
                    })

                CalculatorButton(symbol = "9", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(9))
                    })

                CalculatorButton(symbol = "*", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Orange),
                    onClick = {
                        onAction(CalculatorAction.Operations(CalculatorOperation.Multiply))
                    })
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                CalculatorButton(symbol = "4", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(4))
                    })

                CalculatorButton(symbol = "5", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(5))
                    })

                CalculatorButton(symbol = "6", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(6))
                    })

                CalculatorButton(symbol = "-", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Orange),
                    onClick = {
                        onAction(CalculatorAction.Operations(CalculatorOperation.Subtract))
                    })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                CalculatorButton(symbol = "1", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(1))
                    })

                CalculatorButton(symbol = "2", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(2))
                    })

                CalculatorButton(symbol = "3", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(3))
                    })

                CalculatorButton(symbol = "+", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Orange),
                    onClick = {
                        onAction(CalculatorAction.Operations(CalculatorOperation.Add))
                    })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                CalculatorButton(symbol = "0", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Number(0))
                    })

                CalculatorButton(symbol = ".", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Decimal)
                    })

                CalculatorButton(symbol = "=", modifier = modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .background(Color.DarkGray),
                    onClick = {
                        onAction(CalculatorAction.Result)
                    })
            }
        }
    }
}