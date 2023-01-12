package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.stations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.StationsDataViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase.FavoriteViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase.entitys.Favorite
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.Marker
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.MarkerViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.UserViewModel

@Composable
fun InfoStation(navController: NavController, stationID: Int, stationName: String) {
    val userViewModel: UserViewModel = viewModel()
    val users = userViewModel.readAllData.observeAsState()
    val infoTypeViewModel: StationsDataViewModel = viewModel()
    val info = infoTypeViewModel.getStationsData(stationID).observeAsState()
    val favoriteViewModel: FavoriteViewModel = viewModel()
    val favorite = favoriteViewModel.readAllData.observeAsState()
    val markerViewModel: MarkerViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        info.value?.let { item ->
            if (stationName == item.Nome) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = item.Nome,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Informações de Posto",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.color_text_login),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        users.value?.forEach { user ->
                            if (user.isLogin) {
                                favoriteViewModel.insertFavorite(
                                    Favorite(stationID, user.email, item.Nome)
                                )
                            }
                        }
                    }) {
                        Icon(
                            contentDescription = "IconFavorite",
                            imageVector = Icons.Default.Star
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    Text(
                        text = "Morada:",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.color_buttons),
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "${item.Morada}, ${item.codPostal} ${item.municipio}",
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Marca:",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.color_buttons),
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = item.Marca,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Horários:",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.color_buttons),
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Dias Uteis:",
                                fontSize = 18.sp,
                                color = colorResource(id = R.color.color_buttons),
                            )
                            Text(
                                text = item.diasUteis,
                                fontSize = 18.sp,
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Feriados:",
                                fontSize = 18.sp,
                                color = colorResource(id = R.color.color_buttons),
                            )
                            Text(
                                text = item.feriado,
                                fontSize = 18.sp,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Sabados:",
                                fontSize = 18.sp,
                                color = colorResource(id = R.color.color_buttons),
                            )
                            Text(
                                text = item.sabado,
                                fontSize = 18.sp,
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Domingos:",
                                fontSize = 18.sp,
                                color = colorResource(id = R.color.color_buttons),
                            )
                            Text(
                                text = item.domingo,
                                fontSize = 18.sp,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Modo de Pagamento:",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.color_buttons),
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = item.MeiosPagamento,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .background(
                                color = colorResource(id = R.color.color_background_Drawer),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Combustiveis",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.color_text_login),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = item.tipoCombustivel,
                                fontSize = 18.sp,
                                color = colorResource(id = R.color.color_buttons),
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = item.preco,
                                fontSize = 18.sp,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    modifier = Modifier
                        .width(300.dp)
                        .size(60.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        markerViewModel.deleteAllMarker();
                        markerViewModel.insertMarker(
                            Marker(
                                item.Nome,
                                item.Latitude,
                                item.Longitude
                            )
                        );
                        navController.navigate(NavigationItems.Home.route)
                    }
                ) {
                    Text(text = "Ver no mapa", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}