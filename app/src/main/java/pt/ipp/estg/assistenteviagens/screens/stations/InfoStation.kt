package pt.ipp.estg.assistenteviagens.screens.stations

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.models.NavigationItems

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun InfoStation(navController: NavController) {
    val isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "E.LECLERC FAFE",
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
            IconButton(onClick = { /*TODO 1 CLICK (SAVE IN ROOM) - 2 CLICK (DELETE IN ROOM)*/ }) {
                if (isFavorite) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_star_24),
                        contentDescription = "IconFavorite",
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_star_outline_24),
                        contentDescription = "IconFavorite",
                    )
                }
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
                text = "R. Cidade de Guimarães, 4820-178 Fafe",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Marca:",
                fontSize = 18.sp,
                color = colorResource(id = R.color.color_buttons),
            )
            Text(
                text = "E-Leclerc",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Dias Uteis:",
                fontSize = 18.sp,
                color = colorResource(id = R.color.color_buttons),
            )
            Text(
                text = "Aberto 24 horas",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Feriados:",
                fontSize = 18.sp,
                color = colorResource(id = R.color.color_buttons),
            )
            Text(
                text = "Aberto 24 horas",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Sabados:",
                fontSize = 18.sp,
                color = colorResource(id = R.color.color_buttons),
            )
            Text(
                text = "Aberto 24 horas",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Domingos:",
                fontSize = 18.sp,
                color = colorResource(id = R.color.color_buttons),
            )
            Text(
                text = "Aberto 24 horas",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Modo de Pagamento:",
                fontSize = 18.sp,
                color = colorResource(id = R.color.color_buttons),
            )
            Text(
                text = "Dineiro",
                fontSize = 18.sp,
            )
        }
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
                    text = "GPL Auto:",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.color_buttons),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "0,899 €/litro",
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Gasóleo simples:",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.color_buttons),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "1,639 €/litro",
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Gasóleo especial:",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.color_buttons),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "1,659 €/litro",
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Gasolina simples 95:",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.color_buttons),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "1,639 €/litro",
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Gasolina 98:",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.color_buttons),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "1,799 €/litro",
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .width(300.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = { navController.navigate(NavigationItems.LocalizationStation.route) }) {
            Text(text = "Ver no mapa", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
