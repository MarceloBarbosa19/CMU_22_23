package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.stations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.brands.BrandsViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.countys.CountysViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.districts.DistrictsViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType.GasTypeViewModel

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
    var auxComb by remember { mutableStateOf(0) }
    var auxMarca by remember { mutableStateOf(0) }
    var auxDist by remember { mutableStateOf(0) }
    var auxMun by remember { mutableStateOf(0) }

    val gasTypesViewModel: GasTypeViewModel = viewModel()
    val brandsViewModel: BrandsViewModel = viewModel()
    val districtsViewModel: DistrictsViewModel = viewModel()
    val countysViewModel: CountysViewModel = viewModel()

    val gasType = gasTypesViewModel.getAllGasTypes().observeAsState()
    val brands = brandsViewModel.getAllBrands().observeAsState()
    val districts = districtsViewModel.getAllDistricts().observeAsState()
    val countys = countysViewModel.getAllCountys().observeAsState()

    val cars = listOf("Renault clio", "Merceds", "Kia")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
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
                placeholder = { Text("Selecione o Carro") },
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
                gasType.value?.forEach { item ->
                    DropdownMenuItem(onClick = {
                        mSelectedTextTypeGas = item.Descritivo
                        auxComb = item.Id
                        mExpandedTypeGas = false
                    }) {
                        Text(text = item.Descritivo)
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
                placeholder = { Text("Selecione Tipo de Posto") },
                trailingIcon = {
                    Icon(
                        imageVector = if (mExpandedTypeStation) {
                            Icons.Filled.KeyboardArrowUp
                        } else {
                            Icons.Filled.KeyboardArrowDown
                        },
                        contentDescription = "Icon Dropdown",
                        modifier = Modifier.clickable {
                            mExpandedTypeStation = !mExpandedTypeStation
                        })
                }
            )
            DropdownMenu(
                expanded = mExpandedTypeStation,
                onDismissRequest = { mExpandedTypeStation = false },
                modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                brands.value?.forEach { item ->
                    DropdownMenuItem(onClick = {
                        mSelectedTextTypeStation = item.Descritivo
                        auxMarca = item.Id
                        mExpandedTypeStation = false
                    }) {
                        Text(text = item.Descritivo)
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
                districts.value?.forEach { item ->
                    DropdownMenuItem(onClick = {
                        mSelectedTextDistrict = item.Descritivo
                        auxDist = item.Id
                        mExpandedDistrict = false
                    }) {
                        Text(text = item.Descritivo)
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
                countys.value?.forEach { item ->
                    if (auxDist == item.IdDistrito) {
                        DropdownMenuItem(onClick = {
                            mSelectedTextCounty = item.Descritivo
                            auxMun = item.Id
                            mExpandedCounty = false
                        }) {
                            Text(text = item.Descritivo)
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        if (mSelectedTextTypeStation.isNotEmpty() &&
            mSelectedTextTypeGas.isNotEmpty() &&
            mSelectedTextDistrict.isNotEmpty() &&
            mSelectedTextCounty.isNotEmpty()) {
            Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .padding(horizontal = 20.dp)
                        .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            enabled = true,
            onClick = {
                navController.navigate(NavigationItems.FoundStation.route + "?auxComb=${auxComb}&auxMarca=${auxMarca}&auxDist=${auxDist}&auxMun=${auxMun}")
            })
            {
                Text(text = "Pesquisar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }else{
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(60.dp)
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                enabled = false,
                onClick = {
                    navController.navigate(NavigationItems.FoundStation.route + "?auxComb=${auxComb}&auxMarca=${auxMarca}&auxDist=${auxDist}&auxMun=${auxMun}")
                })
            {
                Text(text = "Pesquisar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = { navController.navigate(NavigationItems.TopStations.route) }) {
            Text(text = "Postos Económicos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}