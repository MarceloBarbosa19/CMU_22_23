package pt.ipp.estg.assistenteviagens.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.MarkerViewModel

@Composable
fun MarkerOfStations() {
    val markerViewModel: MarkerViewModel = viewModel()
    val marker = markerViewModel.readAllData.observeAsState()
    marker.value?.forEach { info ->
        val markerPosition = LatLng(info.latitude, info.longitude)
        Marker(
            state = rememberMarkerState(position = markerPosition),
            title = info.nome,
            snippet = "Long click to delete",
            onInfoWindowLongClick = {
                markerViewModel.deleteMarker(
                    pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.Marker(
                        info.nome, info.latitude, info.longitude
                    )
                )
            }
        )
    }
}