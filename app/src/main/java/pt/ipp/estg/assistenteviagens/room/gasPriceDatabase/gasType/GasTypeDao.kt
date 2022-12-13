package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GasTypeDao {
    @Query("select * from GasTypeDB")
    fun getGasType(): LiveData<List<GasTypeDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGas(gasType: GasTypeDB)
}