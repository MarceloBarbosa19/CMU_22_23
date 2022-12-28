package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.AuthNavigationItems

@Composable
fun MainScreen(navController: NavHostController) {
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(250.dp)
                .clip(RoundedCornerShape(percent = 10)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LogoApp",
        )
        Spacer(modifier = Modifier.size(50.dp))
        Text(
            text = "Where you",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.main_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "want to go ?",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.main_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(50.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .width(286.dp)
                    .size(60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                onClick = { navController.navigate(AuthNavigationItems.Register.route)}) {
                Text(text = "CREATE AN ACCOUNT", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                modifier = Modifier
                    .width(286.dp)
                    .size(60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                onClick = {navController.navigate(AuthNavigationItems.Login.route)}) {
                Text(text = "LOGIN", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

}