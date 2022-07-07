package com.example.troniks

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collect

@Composable
fun MainScreen(){
    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect {event ->
            when(event) {
                is MainViewModel.ValidationEvent.Success ->
                    Toast.makeText(
                        context,
                        "Registration Successful",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = state.email, onValueChange = {
            viewModel.onEvent(RegistrationFormEvents.EmailChanged(it))
        },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth(),
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

        TextField(value = state.password, onValueChange = {
            viewModel.onEvent(RegistrationFormEvents.PasswordChanged(it))
        },
            isError = state.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
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

        TextField(value = state.repeatedPassword, onValueChange = {
            viewModel.onEvent(RegistrationFormEvents.RepeatedPasswordChanged(it))
        },
            isError = state.repeatedPassword != null,
            modifier = Modifier.fillMaxWidth(),
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
                    viewModel.onEvent(RegistrationFormEvents.AcceptedTerms(it))
                } )

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Accept Terms")

        }
        if (state.termsError != null) {
            Text(text = state.termsError,
                color = MaterialTheme.colors.error)
        }

        Button(onClick = {
            viewModel.onEvent(RegistrationFormEvents.Submit)
        }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Submit")
        }

    }


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
fun TroniksIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {} ){
    Image(
        imageVector = icon,
        contentDescription = "Arrow Back",
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeTextPreview(){
    WelcomeText()
}