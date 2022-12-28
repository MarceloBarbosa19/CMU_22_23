package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.stations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pt.ipp.estg.assistenteviagens.R

@Composable
fun LocalizationStation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.map_img),
            contentDescription = "EXMap",
            contentScale= ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Icon(
            imageVector = Icons.Default.LocalGasStation,
            contentDescription = "Icon Pin",
            modifier = Modifier.padding(start = 227.dp, top = 316.dp)
        )
    }
}
