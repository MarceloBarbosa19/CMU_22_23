package pt.ipp.estg.assistenteviagens.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pt.ipp.estg.assistenteviagens.maps.ScaffolMap
import pt.ipp.estg.assistenteviagens.maps.LocationUtils.RequestPerms

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        RequestPerms(context)
        ScaffolMap()
    }
}