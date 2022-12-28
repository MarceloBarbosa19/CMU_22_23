package pt.ipp.estg.assistenteviagens.navigation.utils.topsBars

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.AuthNavigationItems

@Composable
fun TopBarLogin( navController: NavHostController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.color_background_Drawer),
        contentColor = Color.Black,
    ) {
        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigate(AuthNavigationItems.Home.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "ArrowBackIcon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 5.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AuthNavigationItems.Login.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TopBarRegister( navController: NavHostController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.color_background_Drawer),
        contentColor = Color.Black,
    ) {
        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigate(AuthNavigationItems.Home.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "ArrowBackIcon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 5.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AuthNavigationItems.Register.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}