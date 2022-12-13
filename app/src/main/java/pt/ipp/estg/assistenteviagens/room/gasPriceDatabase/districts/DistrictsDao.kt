package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.districts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DistrictsDao {
    @Query("select * from DistrictsDB")
    fun getDistricts(): LiveData<List<DistrictsDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDistricts(districts: DistrictsDB)
}