package pt.ipp.estg.assistenteviagens.maps

import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.countys.CountysViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.StationsDataViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsSearch.StationsSearchViewModel
import java.util.*
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.allStations.MarkersStationsDatabase
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.Marker
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.allStations.MarkersStationsViewModel

@Composable
fun MarkerOfStationsForCity(newLocation: Location) {
    val geocoder = Geocoder(LocalContext.current, Locale.getDefault())
    val addresses: List<Address>?
    val lat = newLocation.latitude
    val long = newLocation.longitude

    addresses = geocoder.getFromLocation(lat, long, 1)
    val city = addresses?.get(0)?.locality

    val countysViewModel: CountysViewModel = viewModel()
    val pesquisaViewModel: StationsSearchViewModel = viewModel()
    val infoTypeViewModel: StationsDataViewModel = viewModel()
    val markersStationsViewModel: MarkersStationsViewModel = viewModel()
    val markers = markersStationsViewModel.readAllData.observeAsState()

    var idValue by remember { mutableStateOf(0) }

    val countys = countysViewModel.getAllCountys().observeAsState()
    val targetCounty = countys.value?.find { county -> county.Descritivo == city }
    if (targetCounty != null) {
        idValue = targetCounty.Id
        val pesquisa = pesquisaViewModel.getAllSearchStations(idValue).observeAsState()
        pesquisa.value?.forEach { search ->
            val info = infoTypeViewModel.getStationsData(search.Id).observeAsState()
            info.value?.let { info ->
                markersStationsViewModel.insertMarker(
                    Marker(info.Nome, info.Latitude, info.Longitude)
                )
            }
        }
    }
    //if(markers.value?.isEmpty() == true){
        markers.value?.forEach { item ->
            val info = infoTypeViewModel.getStationsDataByName(item.nome).observeAsState()
            info.value?.let { info ->
                if (info.municipio == city) {
                    val markerPosition = LatLng(info.Latitude, info.Longitude)
                    MapMarker(
                        position = markerPosition,
                        title = info.Nome,
                        snippet = info.Marca,
                        context = LocalContext.current,
                        iconResourceId = R.drawable.ic_baseline_local_gas_station_24
                    )
                }
            }
        }
    //}
}