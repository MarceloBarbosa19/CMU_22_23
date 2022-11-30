package pt.ipp.estg.assistenteviagens.screens.stations

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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.models.NavigationItems

@Composable
fun SearchStations(navController: NavController) {
    var mExpandedCar by remember { mutableStateOf(false) }
    var mExpandedTypeGas by remember { mutableStateOf(false) }
    var mExpandedTypeStation by remember { mutableStateOf(false) }
    var mExpandedDistrict by remember { mutableStateOf(false) }
    var mExpandedCounty by remember { mutableStateOf(false) }

    var mSelectedTextCar by remember { mutableStateOf("") }
    var mSelectedTextTypeGas by remember { mutableStateOf("") }
    var mSelectedTextTypeStation by remember { mutableStateOf("") }
    var mSelectedTextDistrict by remember { mutableStateOf("") }
    var mSelectedTextCounty by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val cars = listOf("Renault Clio", "Kia XCeed", "Mercedes 320 CDi")
    val typeGas =
        listOf("Gasoleo Simples", "Gasoleo Especial", "Gasolina Simples 95", "Gasoleo Especial 95")
    val typeStation = listOf("Galp", "BP")
    val district = listOf("Braga", "Porto")
    val county = listOf("Felgueiras", "Fafe")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = mSelectedTextCar,
            onValueChange = { mSelectedTextCar = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text("Selecione Carro") },
            trailingIcon = {
                Icon(
                    imageVector = if (mExpandedCar) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpandedCar = !mExpandedCar })
            }
        )
        DropdownMenu(
            expanded = mExpandedCar,
            onDismissRequest = { mExpandedCar = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            cars.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedTextCar = item
                    mExpandedCar = false
                }) {
                    Text(text = item)
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = mSelectedTextTypeGas,
            onValueChange = { mSelectedTextTypeGas = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            placeholder = { Text("Selecione Tipo de Combustivel") },
            trailingIcon = {
                Icon(
                    imageVector = if (mExpandedTypeGas) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpandedTypeGas = !mExpandedTypeGas })
            }
        )
        DropdownMenu(
            expanded = mExpandedTypeGas,
            onDismissRequest = { mExpandedTypeGas = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            typeGas.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedTextTypeGas = item
                    mExpandedTypeGas = false
                }) {
                    Text(text = item)
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = mSelectedTextTypeStation,
            onValueChange = { mSelectedTextTypeStation = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            placeholder = { Text("Selecione Marca do Posto:") },
            trailingIcon = {
                Icon(
                    imageVector = if (mExpandedTypeStation) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpandedTypeStation = !mExpandedTypeStation })
            }
        )
        DropdownMenu(
            expanded = mExpandedTypeStation,
            onDismissRequest = { mExpandedTypeStation = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            typeStation.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedTextTypeStation = item
                    mExpandedTypeStation = false
                }) {
                    Text(text = item)
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = mSelectedTextDistrict,
            onValueChange = { mSelectedTextDistrict = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            placeholder = { Text("Selecione Distrito") },
            trailingIcon = {
                Icon(
                    imageVector = if (mExpandedDistrict) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpandedDistrict = !mExpandedDistrict })
            }
        )
        DropdownMenu(
            expanded = mExpandedDistrict,
            onDismissRequest = { mExpandedDistrict = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            district.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedTextDistrict = item
                    mExpandedDistrict = false
                }) {
                    Text(text = item)
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = mSelectedTextCounty,
            onValueChange = { mSelectedTextCounty = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            placeholder = { Text("Selecione Municipio") },
            trailingIcon = {
                Icon(
                    imageVector = if (mExpandedCounty) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = "Icon Dropdown",
                    modifier = Modifier.clickable { mExpandedCounty = !mExpandedCounty })
            }
        )
        DropdownMenu(
            expanded = mExpandedCounty,
            onDismissRequest = { mExpandedCounty = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            county.forEach { item ->
                DropdownMenuItem(onClick = {
                    mSelectedTextCounty = item
                    mExpandedCounty = false
                }) {
                    Text(text = item)
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            modifier = Modifier
                .width(300.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = { navController.navigate(NavigationItems.FoundStation.route) }) {
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
            onClick = { navController.navigate(NavigationItems.TopStations.route) }) {
            Text(text = "Top Postos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
