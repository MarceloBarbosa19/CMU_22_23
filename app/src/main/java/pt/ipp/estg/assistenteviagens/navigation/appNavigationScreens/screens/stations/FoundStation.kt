package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.stations

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.R

@Composable
fun FoundStation(navController: NavController) {
    //WILL RETURN LIST OF GAS STATIONS FOUND
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //For each card (LazyColum with listof)- take from API
        Column(
            modifier = Modifier
                .width(302.dp)
                .height(154.dp)
                .background(
                    color = colorResource(id = R.color.color_background_Drawer),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable { navController.navigate(NavigationItems.InfoStation.route) },
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "E.LECLERC FAFE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Braga", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Fafe", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Gasóleo simples:", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1,666 €", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Gasóleo simples:", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1,666 €", fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        //Delete after
        //For each card- take from API
        Column(
            modifier = Modifier
                .width(302.dp)
                .height(154.dp)
                .background(
                    color = colorResource(id = R.color.color_background_Drawer),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable { navController.navigate(NavigationItems.InfoStation.route) },
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "E.LECLERC FAFE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Braga", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Fafe", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Gasóleo simples:", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1,666 €", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Gasóleo simples:", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1,666 €", fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        //For each card- take from API
        Column(
            modifier = Modifier
                .width(302.dp)
                .height(154.dp)
                .background(
                    color = colorResource(id = R.color.color_background_Drawer),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable { navController.navigate(NavigationItems.InfoStation.route) },
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "E.LECLERC FAFE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Braga", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Fafe", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Gasóleo simples:", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1,666 €", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Gasóleo simples:", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "1,666 €", fontSize = 14.sp)
            }
        }
    }
}
