package com.example.tests

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tests.ui.theme.TestsTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Preview_OutlineDatePicker()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun LocatedPicker(
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit?)? = null,
    value: LocalDate,
    formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"),
    icon: ImageVector = Icons.Filled.DateRange,
    iconContentDescription: String = stringResource(id = R.string.DatePickerIcon_ContentDescription),
    onValueChange: (LocalDate) -> Unit

    ) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val isDatePickerDisplayed = remember { mutableStateOf(false) }

    val datePickerDialog = DatePickerDialog (
        context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onValueChange(LocalDate.of(year, month, dayOfMonth))
            isDatePickerDisplayed.value = false
            focusManager.clearFocus()
        }, value.year, value.monthValue, value.dayOfMonth)

    datePickerDialog.setOnDismissListener {
        isDatePickerDisplayed.value = false
        focusManager.clearFocus()
    }
    if(!isDatePickerDisplayed.value) {
        datePickerDialog.show()
    }

    OutlinedTextField(
        modifier = modifier.onFocusChanged {  isDatePickerDisplayed.value = it.isFocused },
        label = {
                Text("$label")
        },
        value = value.format(formatter),
        onValueChange = {onValueChange(LocalDate.parse(it, formatter))},
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = iconContentDescription,
                modifier = Modifier.clickable { isDatePickerDisplayed.value = true }
            )
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun Preview_OutlineDatePicker() {
    val date1 = remember { mutableStateOf(LocalDate.now()) }
    val date2 = remember { mutableStateOf(LocalDate.now()) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            LocatedPicker(
                label = { Text("Date") },
                value = date1.value,
                onValueChange = { date1.value = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            LocatedPicker(
                label = { Text("Date") },
                value = date2.value,
                formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG),
                onValueChange = { date2.value = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestsTheme {
        Greeting("Android")
    }
}