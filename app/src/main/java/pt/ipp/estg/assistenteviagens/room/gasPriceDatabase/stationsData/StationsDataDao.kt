package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.entitys.StationsDataDB

@Dao
interface StationsDataDao {
    @Query("select * from StationsDataDB")
    fun getStationsData(): LiveData<StationsDataDB>

    @Query("select * from StationsDataDB where Nome= :stationName")
    fun getStationsDataByName(stationName: String): LiveData<StationsDataDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStationsData(info: StationsDataDB)

    @Query("delete from StationsDataDB")
    suspend fun deleteAllStationsData()
}