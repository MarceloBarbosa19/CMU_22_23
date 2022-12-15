package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.topStations

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopStationsDao {
    @Query("select * from TopStationsDB")
    fun getTopStation(): LiveData<List<TopStationsDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTopStations(topStations: TopStationsDB)
}