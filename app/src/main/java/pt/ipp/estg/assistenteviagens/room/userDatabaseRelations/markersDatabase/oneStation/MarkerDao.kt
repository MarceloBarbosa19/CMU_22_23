package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarkerDao {
    @Query("select * from Marker")
    fun loadMarkers(): LiveData<List<Marker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarkers(marker: Marker)

    @Delete
    suspend fun deleteMarkers(marker: Marker)

    @Query("delete from Marker")
    suspend fun deleteAllMarkers()
}
