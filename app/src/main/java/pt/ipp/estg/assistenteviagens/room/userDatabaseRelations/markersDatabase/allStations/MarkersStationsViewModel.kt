package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.allStations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.Marker

class MarkersStationsViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData: LiveData<List<Marker>>
    private var repository: MarkersStationsRepository

    init {
        val markerDao = MarkersStationsDatabase.getInstance(application).markerDao()
        repository = MarkersStationsRepository(markerDao)
        readAllData = repository.getAllMarkers()
    }

    fun insertMarker(marker: Marker) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMarker(marker)
        }
    }

    fun deleteMarker(marker: Marker) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMarker(marker)
        }
    }

    fun deleteAllMarker() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMarker()
        }
    }
}