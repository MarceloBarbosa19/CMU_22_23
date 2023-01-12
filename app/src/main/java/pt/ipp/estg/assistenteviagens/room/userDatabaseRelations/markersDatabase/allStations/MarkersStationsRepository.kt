package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.allStations

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.Marker
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.MarkerDao

class MarkersStationsRepository(private val markersDao: MarkerDao) {

    fun getAllMarkers(): LiveData<List<Marker>> {
        return markersDao.loadMarkers()
    }

    suspend fun insertMarker(marker: Marker) {
        markersDao.insertMarkers(marker)
    }

    suspend fun deleteMarker(marker: Marker) {
        markersDao.deleteMarkers(marker)
    }

    suspend fun deleteAllMarker() {
        markersDao.deleteAllMarkers()
    }
}