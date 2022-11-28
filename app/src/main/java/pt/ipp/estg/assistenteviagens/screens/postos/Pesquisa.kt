package pt.ipp.estg.assistenteviagens.screens.postos

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.RegisterScreen

@Composable
fun Pesquisa() {
    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }

    val cars = listOf("Renault Clio", ",Kia XCEED")
    val typeGas = listOf("Renault Clio", ",Kia XCEED")
    val typeStation = listOf("Renault Clio", ",Kia XCEED")
    val district = listOf("Renault Clio", ",Kia XCEED")
    val county = listOf("Renault Clio", ",Kia XCEED")

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Carro:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            label = { Text("Selecione") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(300.dp)
        ) {
            cars.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedText = item
                    mExpanded = false
                }) {
                    Text(text = item)
                }
            }
        }

        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Tipo de Combustivel:", fontSize = 20.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            label = { Text("Selecione") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(300.dp)
        ) {
            typeGas.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedText = item
                    mExpanded = false
                }) {
                    Text(text = item)
                }
            }
        }

        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Marca do Posto:", fontSize = 20.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            label = { Text("Selecione") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(300.dp)
        ) {
            typeStation.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedText = item
                    mExpanded = false
                }) {
                    Text(text = item)
                }
            }
        }

        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Distrito:", fontSize = 20.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            label = { Text("Selecione") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(300.dp)
        ) {
            district.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedText = item
                    mExpanded = false
                }) {
                    Text(text = item)
                }
            }
        }

        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Municipio:", fontSize = 20.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            label = { Text("Selecione") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(300.dp)
        ) {
            county.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedText = item
                    mExpanded = false
                }) {
                    Text(text = item)
                }
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            modifier = Modifier
                .width(300.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Pesquisar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.size(15.dp))
        Button(
            modifier = Modifier
                .width(300.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Top Postos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Pesquisa()
}