package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarkerViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData: LiveData<List<Marker>>
    private var repository: MarkerRepository

    init {
        val markerDao = MarkerDatabase.getInstance(application).markerDao()
        repository = MarkerRepository(markerDao)
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