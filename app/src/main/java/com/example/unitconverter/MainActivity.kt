package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("Euro")}
    var outputUnit by remember{ mutableStateOf("Euro")}
    var iExpanded by remember{ mutableStateOf(false)}
    var oExpended by remember{ mutableStateOf(false)}
    val conversionFactor = remember {mutableStateOf(1.0)}
    val oConversionFactor = remember {mutableStateOf(1.0)}


    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0/oConversionFactor.value).roundToInt() /100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Currency Converter",
            style= MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()

        },
            label = { Text("Enter value: ")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box
            Box{
                Button(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Euro") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Euro"
                            conversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("USD") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "USD"
                            conversionFactor.value = 0.93
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("HRK") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "HRK"
                            conversionFactor.value = 0.1327
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Pound") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Pound"
                            conversionFactor.value = 1.17
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("CZK") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "CZK"
                            conversionFactor.value = 0.039
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { oExpended = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpended, onDismissRequest = { oExpended = false }) {
                    DropdownMenuItem(
                        text = { Text("Euro") },
                        onClick = {
                            oExpended = false
                            outputUnit = "Euro"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("USD") },
                        onClick = {
                            oExpended = false
                            outputUnit = "USD"
                            oConversionFactor.value = 0.93
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("HRK") },
                        onClick = {
                            oExpended = false
                            outputUnit = "HRK"
                            oConversionFactor.value = 0.1327
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Pound") },
                        onClick = {
                            oExpended = false
                            outputUnit = "Pound"
                            oConversionFactor.value = 1.17
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("CZK") },
                        onClick = {
                            oExpended = false
                            outputUnit = "CZK"
                            oConversionFactor.value = 0.039
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",
            style= MaterialTheme.typography.headlineMedium)
    }
}

@Preview (showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
