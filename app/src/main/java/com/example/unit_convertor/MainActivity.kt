package com.example.unit_convertor

import android.graphics.drawable.Icon
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit_convertor.ui.theme.Unit_ConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit_ConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Unit_convertor()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Unit_convertor(){
    var inputvalue by remember {mutableStateOf("")}
    var outputvalue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meter")}
    var outputUnit by remember {mutableStateOf("Meter")}
    var iexpanded by remember{mutableStateOf(false)}
    var oexpanded by remember {mutableStateOf(false)}
    var conversionfactor by remember {mutableStateOf(1.00)}
    val oconversionfactor = remember {mutableStateOf(1.00)}

    val CustomTextStyle = TextStyle(
        fontFamily = FontFamily.Default ,//Replace with desired font family
        fontSize = 16.sp,   //Replace with your desired fontsize
        color = Color.Blue
    )

    fun convertor(){
        // ?: = elvis operator
        //variable is the expression that you want to check for null.
        // If variable is not null, its value is returned.
        // Otherwise, the default value is returned.
        val inputDvalue = inputvalue.toDoubleOrNull() ?:0.0
        val result =( inputDvalue * conversionfactor * 100.000
                /oconversionfactor.value).roundToInt()/100.000
        outputvalue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        )
        {
        //Here all elements should be stacked below each other

        Text("Unit Convertor",
            style = MaterialTheme.typography.headlineMedium,
             modifier = Modifier.padding(5.dp)) //padding
        Spacer(modifier = Modifier.height(16.dp)) //spacing

        OutlinedTextField(value = inputvalue,
            onValueChange =
        {
            inputvalue =it
            convertor()
        },
            label={ Text(text = "Enter Value")}
            )

        //Here goes what should happen when ,value of outlined text Field changes

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box

            Box {
                Button(onClick = { iexpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = " Arrow Down")
                }

                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded= false})
                {

                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            iexpanded = false
                            inputUnit = "Centimeter"
                            conversionfactor =0.01
                            convertor()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            iexpanded = false
                            inputUnit = "Meter"
                            conversionfactor = 1.00
                            convertor()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iexpanded = false
                            inputUnit = "Feet"
                            conversionfactor =0.3048
                            convertor()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            iexpanded = false
                            inputUnit = "Millimeter"
                            conversionfactor =0.001
                            convertor()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { oexpanded=true})
                {
                    Text(outputUnit)
                   Icon(Icons.Default.ArrowDropDown ,contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = { oexpanded= false}) {

                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            oexpanded = false
                            outputUnit = "Centimeter"
                            oconversionfactor.value =0.01
                            convertor()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            oexpanded = false
                            outputUnit = "Meter"
                            oconversionfactor.value = 1.0
                            convertor()

                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oexpanded = false
                            outputUnit = "Feet"
                            oconversionfactor.value =0.3048
                            convertor()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            oexpanded = false
                            outputUnit = "Millimeter"
                            oconversionfactor.value =0.001
                            convertor()
                         }
                    )
                }

            }


        }
        Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = outputvalue ,
                onValueChange =
                {
                   /*TODO*/
                },
                label={ Text(text = "Output Value")}
            )
//
//        Text("Input: $inputvalue $inputUnit")
//        Spacer(modifier = Modifier.height(16.dp))
//        Text("Result: $outputvalue $outputUnit",
//            style = MaterialTheme.typography.headlineSmall)
//            Text("$outputUnit",
//                style =CustomTextStyle)
    }
    }


@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview(){
    Unit_convertor()
}