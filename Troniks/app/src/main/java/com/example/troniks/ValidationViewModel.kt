package com.example.troniks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.troniks.domain.use_cases.ValidateEmail
import com.example.troniks.domain.use_cases.ValidatePassword
import com.example.troniks.domain.use_cases.ValidateRepeatedPassword
import com.example.troniks.domain.use_cases.ValidateTerms
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
): ViewModel() {
    var state by mutableStateOf(RegistrationFormStatus())
    private val validateEventsChannel = Channel<ValidationEvent> ()
    val validationEvents = validateEventsChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvents) {
        when(event) {
            is RegistrationFormEvents.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvents.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvents.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvents.AcceptedTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvents.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPassword = validateRepeatedPassword.execute(state.password,state.repeatedPassword)
        val termResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPassword,termResult
        ).any { it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPassword.errorMessage,
                termsError = termResult.errorMessage
            )
        }
        viewModelScope.launch {
            validateEventsChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }

}