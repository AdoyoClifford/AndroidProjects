package com.example.troniks.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(){

}

@Composable
fun WelcomeText(){
    Column() {
        Text(
            text = "Hello Again!",
            style = MaterialTheme.typography.h1
        )
        Text(
            text = "We would like to know you",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun ArrowBack(){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeTextPreview(){
    WelcomeText()
}