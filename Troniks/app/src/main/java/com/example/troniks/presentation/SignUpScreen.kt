package com.example.troniks.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignIn() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val viewModel = viewModel<MainViewModel>()
        val state = viewModel.state
        val context = LocalContext.current

        LaunchedEffect(key1 = context ) {
            viewModel.validationEvents.collect { event ->
                when(event) {
                    is MainViewModel.ValidationEvent.Success -> {
                        Toast.makeText(context,
                            "Registration Successful",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Hello",
                        modifier = Modifier.padding(4.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("We would like to know you",                        modifier = Modifier.padding(4.dp),
                        fontWeight = FontWeight.Bold)
                }
            }

            OutlinedTextField(value = state.email, onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
            },
                isError = state.emailError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, color = Color.Green)
                    .background(Color.Gray),
                placeholder = {
                    Text(text = "Email")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            if (state.emailError != null) {
                Text(text = state.emailError,
                    color = MaterialTheme.colors.error)
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = state.password, onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
            },
                isError = state.passwordError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, color = Color.Green)
                    .background(Color.Gray),
                placeholder = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            if (state.passwordError != null) {
                Text(text = state.passwordError,
                    color = MaterialTheme.colors.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = state.repeatedPassword, onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
            },
                isError = state.repeatedPassword != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, color = Color.Green)
                    .background(Color.Gray),
                placeholder = {
                    Text(text = " Repeat Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            if (state.repeatedPasswordError != null) {
                Text(text = state.repeatedPasswordError,
                    color = MaterialTheme.colors.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = state.acceptedTerms,
                    onCheckedChange = {
                        viewModel.onEvent(RegistrationFormEvent.AcceptTerms(it))
                    } )

                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Accept Terms")

            }
            if (state.termsError != null) {
                Text(text = state.termsError,
                    color = MaterialTheme.colors.error)
            }

            Button(onClick = {
                viewModel.onEvent(RegistrationFormEvent.Submit)
            }, modifier = Modifier.align(Alignment.End)) {
                Text(text = "Submit")
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignInPreview() {
    SignIn()
}
