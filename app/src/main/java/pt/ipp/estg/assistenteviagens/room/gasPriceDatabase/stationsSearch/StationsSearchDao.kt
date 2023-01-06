package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsSearch

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StationsSearchDao {
    @Query("select * from StationsSearchDB")
    fun getStationsSearch(): LiveData<List<StationsSearchDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStationsSearch(search: StationsSearchDB)

    @Query("delete from StationsSearchDB")
    suspend fun deleteAllStationsSearch()
}