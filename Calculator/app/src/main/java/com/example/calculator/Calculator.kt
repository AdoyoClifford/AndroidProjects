package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Text(text = state.number1 + (state.operation?: "") + state.number2,
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
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    })
            }
        }
    }
}