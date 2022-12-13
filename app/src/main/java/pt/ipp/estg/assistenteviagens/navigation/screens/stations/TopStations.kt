package pt.ipp.estg.assistenteviagens.navigation.screens.stations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType.GasTypeDB
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType.GasTypeViewModel

@Composable
fun TopStations() {
    var dialogOpen by remember { mutableStateOf(false) }
    var aux by remember { mutableStateOf("") }
    var id by remember { mutableStateOf(0) }

    val gasTypesViewModel: GasTypeViewModel = viewModel()
    val gasType = gasTypesViewModel.getAllGasTypes().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "Os Postos Mais Económicos",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.size(15.dp))
        gasType.value?.forEach { item ->
            Button(
                modifier = Modifier
                    .width(300.dp)
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
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //dps fazer o retrofit para fazer uma lista de postos com o determinado combustivel mais barato
                    Row {
                        Text(
                            text = "Nome do Posto",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = "Preço", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Row {
                        Text(
                            text = "Nome do Posto",
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = "Preço", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.size(5.dp))

                    Spacer(modifier = Modifier.size(15.dp))
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