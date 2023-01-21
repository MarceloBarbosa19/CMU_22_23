package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.stations

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.utils.searchButton.SearchViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.StationsDataViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsSearch.StationsSearchViewModel
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

@Composable
fun FoundStation(navController: NavController, combID: Int, marcaID: Int, distID: Int, munID: Int) {
    val mContext = LocalContext.current
    val pesquisaViewModel: StationsSearchViewModel = viewModel()
    val pesquisa =
        pesquisaViewModel.getSearchStations(combID, marcaID, distID, munID).observeAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pesquisa.value?.forEach { item ->
            val infoTypeViewModel: StationsDataViewModel = viewModel()
            val info = infoTypeViewModel.getStationsData(item.Id).observeAsState()
            Spacer(modifier = Modifier.height(15.dp))
            info.value?.let { info ->
                Column(
                    modifier = Modifier
                        .width(302.dp)
                        .height(154.dp)
                        .background(
                            color = colorResource(id = R.color.color_background_Drawer),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            navController.navigate(NavigationItems.InfoStationByName.route + "?stationID=${item.Id}&stationNome=${item.Nome}")
                        },
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.Nome,
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
                        Text(
                            text = info.Distrito,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = info.municipio,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = info.tipoCombustivel, fontSize = 14.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = info.preco, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewFoundStation() {
    AssistenteViagensTheme {
        val navController = rememberNavController()
        FoundStation(navController, 1, 1,1,1)
    }
}