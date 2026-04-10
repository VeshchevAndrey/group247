package com.example.application247

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold() {
                CalculatorScreen(modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier= Modifier){
    val state = rememberSaveable() { mutableStateOf(CalculatorState()) }

    val buttons = arrayOf(
        stringArrayResource(R.array.row_1),
        stringArrayResource(R.array.row_2),
        stringArrayResource(R.array.row_3),
        stringArrayResource(R.array.row_4),
        stringArrayResource(R.array.row_5)
    )

    Column(
        modifier = modifier.fillMaxSize().background(Color(0xFF000000))
    ) {
        Text(
            text = state.value.display,
            modifier = Modifier.fillMaxWidth().weight(2f),
            color = Color(0xFFFFFFFF),
            fontSize = 72.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Right
        )

        val operator = arrayOf("+", "-", "x", "/", "=")
        buttons.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                row.forEach { label ->
                    CalculatorButton(
                        buttonLabel = label,
                        modifier = Modifier.weight(if (label == "0") 2f else 1f),
                        color = (if (label in operator) Color.Red else Color.White),
                        textColor = (if (label in operator) Color.White else Color.Black)
                    ) { handleButtonClick(buttonLabel = label, currentState = state.value) {
                        newState -> state.value = newState
                    } }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    buttonLabel: String,
    modifier: Modifier = Modifier,
    color: Color,
    textColor: Color,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = modifier.padding(1.dp).fillMaxHeight(),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = textColor
        )
    ) {
        Text(
            text = buttonLabel,
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

fun handleButtonClick(
    buttonLabel: String,
    currentState: CalculatorState,
    updateState: (CalculatorState) -> Unit
){
    var newState = currentState

    when (buttonLabel) {
        ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
            if (newState.isNewInput){
                newState = newState.copy(
                    display = if (buttonLabel == ".") "0." else buttonLabel,
                    isNewInput = false
                )
            } else {
                val newDisplay = if ((newState.display == "0") and (buttonLabel != ".")) {
                    buttonLabel
                } else {
                    newState.display + buttonLabel
                }
                newState = newState.copy(display = newDisplay)
            }
        }
        "+", "-", "x", "/" -> {
            val currentNumber = newState.display.toDoubleOrNull() ?: 0.0

            newState = if ((newState.previousValue == null) or (newState.currentOperator == null)) {
                newState.copy(
                    previousValue = currentNumber,
                    currentOperator = buttonLabel,
                    isNewInput = true
                )
            } else {
                val result = calculate(
                    a = newState.previousValue!!,
                    b = currentNumber,
                    operator = newState.currentOperator!!)
                newState.copy(
                    display = result.toString().removeSuffix(".0"),
                    previousValue = result,
                    currentOperator = buttonLabel,
                    isNewInput = true
                )
            }
        }
        "=" -> {
            if ((newState.currentOperator != null) and (newState.previousValue != null)) {
                val currentNumber = newState.display.toDoubleOrNull() ?: 0.0
                val result = calculate(
                    a = newState.previousValue!!,
                    b = currentNumber,
                    operator = newState.currentOperator!!)

                newState = newState.copy(
                    display = result.toString().removeSuffix(".0"),
                    previousValue = null,
                    currentOperator = null,
                    isNewInput = true
                )
            }
        }
        "+/-" -> {
            val value = newState.display.toDoubleOrNull() ?: 0.0
            newState = newState.copy(display = (-value).toString().removeSuffix(".0"))
        }
        "%" -> {
            val value = newState.display.toDoubleOrNull() ?: 0.0
            newState = newState.copy(display = (value / 100).toString().removeSuffix(".0"))
        }
        "C" -> newState = CalculatorState()
    }

    updateState(newState)
}

fun calculate(a: Double, b: Double, operator: String): Double {
    return when (operator) {
        "+" -> a + b
        "-" -> a - b
        "x" -> a * b
        "/" -> if (b != 0.0) a / b else 0.0
        else -> b
    }
}

@Parcelize
data class CalculatorState(
    val display: String = "0",
    val previousValue: Double? = null,
    val currentOperator: String? = null,
    val isNewInput: Boolean = true
): Parcelable

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}