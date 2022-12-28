package pt.ipp.estg.assistenteviagens.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems

@Composable
fun SuggestScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = "O Que Procura?",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
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
            onClick = { navController.navigate(NavigationItems.SearchStation.route) }
        ) {
            Text(text = "Postos de Combustiveis", fontSize = 20.sp)
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
            enabled = false,
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Restaurantes", fontSize = 20.sp)
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
            enabled = false,
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Multibancos", fontSize = 20.sp)
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
            enabled = false,
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Museus", fontSize = 20.sp)
        }
    }
}