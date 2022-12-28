package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.stations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType.GasTypeViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.topStations.TopStationsViewModel

@Composable
fun TopStations() {
    val mContext = LocalContext.current
    var dialogOpen by remember { mutableStateOf(false) }
    var aux by remember { mutableStateOf("") }
    var id by remember { mutableStateOf(0) }

    val gasTypeViewModel: GasTypeViewModel = viewModel()
    val gasType = gasTypeViewModel.getAllGasTypes().observeAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Os Postos Mais Económicos",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(15.dp))
        gasType.value?.forEach { item ->
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(5.dp),
                onClick = { dialogOpen = true; aux = item.Descritivo; id = item.Id }) {
                Text(text = item.Descritivo, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
    if (dialogOpen) {
        Dialog(
            onDismissRequest = { dialogOpen = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 5.dp),
                shape = RoundedCornerShape(size = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val topStationsViewModel: TopStationsViewModel = viewModel()
                    val topStations = topStationsViewModel.getAllTopStations(id).observeAsState()
                    topStations.value?.forEach { item ->
                        if (aux == item.Combustivel) {
                            Row {
                                TextButton(onClick = { /*TODO: SHOW INFO OF STATION */}) {
                                    Text(
                                        text = item.Nome,
                                        fontSize = 15.sp,
                                        modifier = Modifier.weight(3f),
                                        color = Color.Black
                                    )
                                    Text(
                                        text = item.Preco,
                                        fontSize = 15.sp,
                                        color = colorResource(id = R.color.color_buttons),
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(5.dp),
                        onClick = { dialogOpen = false }) {
                        Text(text = "Close", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}