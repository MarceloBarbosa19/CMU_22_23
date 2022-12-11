package pt.ipp.estg.assistenteviagens.navigation

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pt.ipp.estg.assistenteviagens.Navigation
import pt.ipp.estg.assistenteviagens.R


@Composable
fun FavoritesScreen() {
    var dialogOpenDetails by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Text(
            modifier= Modifier
                .padding(vertical = 40.dp, horizontal = 20.dp),
            text= "Favoritos:", fontSize= 35.sp, fontWeight = FontWeight.Bold
        )
        Row(modifier = Modifier
            .padding(horizontal = 50.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_circle_24),
                contentDescription = "IconCar"
            )
            Spacer(modifier = Modifier.width(8.dp))
            ClickableText(
                text = AnnotatedString("E.LECLERC FAFE"),
                onClick = { dialogOpenDetails = true }
            )
        }
    }
    if(dialogOpenDetails){
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
                    //dps fazer o retrofit para fazer uma lista de postos com o determinado combustivel mais barato, por agora vai assim
                    Row() {
                        Text(text = "Informações do posto", fontSize = 20.sp,fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.padding(horizontal = 30.dp))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_star_24),
                            contentDescription = "IconStar")
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Column() {
                        Text(text = "Morada:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Marca:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Dias Uteis:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Feriados:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Sabados:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Domingos:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Modos de pagamentos:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row() {
                        Text(text = "Combustiveis", fontSize = 20.sp,fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.padding(horizontal = 65.dp))
                        Image(painter = painterResource(id = R.drawable.ic_baseline_local_gas_station_24),
                            contentDescription = "IconGasStation")
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Column() {
                        Text(text = "Gasóleo Simples:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Gasóleo Aditivado:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Gasolina 95:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column() {
                        Text(text = "Gasolina 98:", color = Color.Blue)
                        Text(text = "XXXXXXXXXXXXX")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        modifier=Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(5.dp),
                        onClick = { }) {
                        Text(text = "Ver no mapa", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteScreen() {
    FavoritesScreen()

}