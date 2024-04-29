package com.example.unitconverter

import android.graphics.Paint.Style
import android.os.Bundle
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {    //it is a composable(that can be seen on screen),it is a function that have a certain behaviour
                //theme of our app(make sure that right color theme is used in our app)
                // A surface container using the 'background' color from the theme
                Surface(
                    //background of our app
                    modifier = Modifier.fillMaxSize(), //modifier define how the certain setting of surface are going to be
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){   //is also a composable

    var iValue by remember  {mutableStateOf("")}  //mutable state of type string
    var ovalue by remember {mutableStateOf("")}
    var iUnit by remember {mutableStateOf("Meter")}
    var oUnit by remember{ mutableStateOf("Meter")}
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    var conversionFactor by remember {mutableStateOf(1.0)}
    var OConversionFactor by remember {mutableStateOf(1.0)}

    fun convertUnits(){
        val inputValueDouble=iValue.toDoubleOrNull()?:0.0
        val result=
            ((inputValueDouble * conversionFactor * 100.0) / OConversionFactor) / 100.0  //can use.roundToINT to give result without giving long result
        ovalue= result.toString()

    }

    Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment =Alignment.CenterHorizontally)
    { //is composable (composable can be inside another composable)
         //Modifier can control the properties like padding,size alignment
        //UI elements will be stacked below each other
        Text(text = "UNIT CONVERTER",style=MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = iValue ,onValueChange ={iValue=it ;convertUnits()}  ,label ={Text(text ="Enter Here")} )

        //onvaluechange tells What will happen once the value gets changed)
        // 3 different text composable field:Basic,Underlined,Text
        Spacer(modifier = Modifier.height(16.dp))//used to give spaces or distance betwn elements(can be done by padding modifier or spacer element)

        Row{
            //UI elements will be next to each other
            //Toast :Popup that we see in app(to use:want Context),Context:is the particular area of application where something should happen


            Box{
                //Box is a layout element that arranges and stack the composable on top of each other
                // used to crate custom and complex layouts(eg:Drop Down Menu)
                Button(onClick = {iExpanded=true})
                {
                    Text(text = iUnit)
                    Icon(Icons.Default.KeyboardArrowDown,contentDescription = null)
                }

                DropdownMenu(expanded =iExpanded, onDismissRequest = {iExpanded=false}) //dismiss request:What should happen when we dismiss menu
                {
                    //list of item in menu
                    DropdownMenuItem(text ={Text(text = "Kilometers")}, onClick ={iExpanded=false ;iUnit="Kilometers" ;conversionFactor=1000.0 ;convertUnits()})
                    DropdownMenuItem(text ={Text(text ="Meters")}, onClick = {iExpanded=false ;iUnit="Meters" ;conversionFactor=1.0 ;convertUnits()})
                    DropdownMenuItem(text ={Text(text = "Centimeters")}, onClick = {iExpanded=false ;iUnit="Centimeters" ;conversionFactor=.01 ;convertUnits()})
                    DropdownMenuItem(text ={Text(text = "Millimeters")}, onClick = {iExpanded=false ;iUnit="Millimeters" ;conversionFactor=.001 ;convertUnits()})
                }
            }

            Spacer(modifier = Modifier.width(50.dp))  //18.dp is horizontal

            Box{
                Button(onClick = {oExpanded=true})
                {
                    Text(text = oUnit)
                    Icon(Icons.Default.KeyboardArrowDown,contentDescription = null) //contDesc:describes what the icon will do or represent when hover over it
                }

                DropdownMenu(expanded= oExpanded, onDismissRequest= {oExpanded=false})//dismiss request:What should happen when we dismiss menu
                {
                    //list of item in menu
                    DropdownMenuItem(text ={Text(text = "Kilometers")}, onClick= {oExpanded=false ;oUnit="Kilometers" ;OConversionFactor=1000.0 ;convertUnits()})
                    DropdownMenuItem(text ={Text(text = "Meters")}, onClick = {oExpanded=false ;oUnit="Meters" ;OConversionFactor=1.0 ;convertUnits()})
                    DropdownMenuItem(text ={Text(text = "Centimeters")}, onClick = {oExpanded=false ;oUnit="Centimeters" ;OConversionFactor=.01 ;convertUnits()})
                    DropdownMenuItem(text ={Text(text = "Millimeters")}, onClick = {oExpanded=false ;oUnit="Millimeters" ;OConversionFactor=.001 ;convertUnits()})
                }

            }

            Spacer(modifier = Modifier.height(60.dp))
        }

         //can add style properties that any text composable can have
        Text(text = "Result:-> $ovalue $oUnit" ,style=MaterialTheme.typography.titleLarge)
    }
}

@Preview(showBackground = true) //show background change the background to white
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
