package pt.ipp.estg.assistenteviagens.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.maps.MapsGoogle

@Composable
fun HomeScreen() {
    var dialogOpen by remember { mutableStateOf(false) }
    var carCrash by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MapsGoogle()
        Image(
            modifier = Modifier
                .padding(start = 372.dp, top = 9.dp)
                .size(25.dp)
                .clickable { dialogOpen = true }
            ,painter = painterResource(id = R.drawable.ic_baseline_add_alert_24),
            contentDescription = "IconAlert",
        )
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
                    Column() {
                       Text(text = "Acidente", color = Color.Red, fontSize = 25.sp)
                        Column(modifier = Modifier
                            .padding(horizontal = 20.dp)) {
                            Text(text = "Distância:   1km")
                            Text(text = "Atraso:  00:20h")
                        }
                    }
                    //dps fazer o retrofit para fazer uma lista de postos com o determinado combustivel mais barato, por agora vai assim
                    Spacer(modifier = Modifier.size(15.dp))
                    Row() {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_red)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { carCrash = true; dialogOpen = false}) {
                            Text(text = "Create Alert", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpen = false }) {
                            Text(text = "Cancel", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                        }
                    }
                }
            }
        }
    if (carCrash){
        Image(
            modifier = Modifier
                .padding(start = 260.dp, top = 120.dp)
                .size(30.dp)
            ,painter = painterResource(id = R.drawable.ic_baseline_car_crash_24),
            contentDescription = "IconCarCrash",
        )
    }
    }


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()

}