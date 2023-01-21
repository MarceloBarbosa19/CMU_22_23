package pt.ipp.estg.assistenteviagens.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels.FirestoreFavViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.StationsDataViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.Marker
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.MarkerViewModel


@Composable
fun FavoritesScreen(navController: NavController) {
    var idValue by remember { mutableStateOf(0) }
    var dialogOpenDetails by remember { mutableStateOf(false) }

    val infoTypeViewModel: StationsDataViewModel = viewModel()
    val markerViewModel: MarkerViewModel = viewModel()
    val firestoreFavViewModel: FirestoreFavViewModel = viewModel()
    val email = Firebase.auth.currentUser?.email!!
    val favData = firestoreFavViewModel.getFavsData(email)
    val favs by favData.observeAsState(initial = listOf())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 40.dp, horizontal = 20.dp),
            text = "Favoritos:", fontSize = 35.sp, fontWeight = FontWeight.Bold
        )
        favs?.forEach { fav ->
            Divider(color = Color.Gray)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.padding(horizontal = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(13.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_circle_24),
                    contentDescription = "IconDot"
                )
                Spacer(modifier = Modifier.width(8.dp))
                ClickableText(
                    modifier = Modifier.weight(1F),
                    text = AnnotatedString(fav.name),
                    onClick = { dialogOpenDetails = true; idValue = fav.idStation }
                )
                IconButton(onClick = {
                    firestoreFavViewModel.deleteFav(email, fav.name)
                }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = "closeIcon"
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray)
        }


    }
    if (dialogOpenDetails) {
        Dialog(
            onDismissRequest = { dialogOpenDetails = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                ) {
                    Row() {
                        Text(
                            text = "Informações do posto",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    val infoTypeViewModel: StationsDataViewModel = viewModel()
                    val info = infoTypeViewModel.getStationsData(idValue).observeAsState()
                    info.value?.let { inf ->
                        Text(
                            text = "Morada:",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.color_buttons),
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            text = "${inf.Morada}, ${inf.codPostal} ${inf.municipio}",
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
                            text = inf.Marca,
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
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
                                    text = inf.diasUteis,
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
                                    text = inf.feriado,
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
                                    text = inf.sabado,
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
                                    text = inf.domingo,
                                    fontSize = 18.sp,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Modo de Pagamento:",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.color_buttons),
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            text = inf.MeiosPagamento,
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row() {
                            Text(
                                text = "Combustiveis",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.weight(1F))
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_local_gas_station_24),
                                contentDescription = "IconGasStation"
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = inf.tipoCombustivel,
                                fontSize = 18.sp,
                                color = colorResource(id = R.color.color_buttons),
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = inf.preco, fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(
                                    id = R.color.color_buttons
                                )
                            ),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = {
                                markerViewModel.deleteAllMarker();
                                markerViewModel.insertMarker(
                                    Marker(
                                        inf.Nome,
                                        inf.Latitude,
                                        inf.Longitude
                                    )
                                );
                                navController.navigate(NavigationItems.Home.route)
                            }) {
                            Text(
                                text = "Ver no mapa",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteScreen() {
    val navController = rememberNavController()
    FavoritesScreen(navController = navController)
}