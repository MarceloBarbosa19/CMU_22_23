package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation

import androidx.lifecycle.LiveData

class MarkerRepository(private val markersDao: MarkerDao) {

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