package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.allStations

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MarkersStationsDao {
    @Query("select * from MarkersStations")
    fun loadMarkers(): LiveData<List<MarkersStations>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarkers(markers: MarkersStations)

    @Delete
    suspend fun deleteMarkers(markers: MarkersStations)

    @Query("delete from Marker")
    suspend fun deleteAllMarkers()
}