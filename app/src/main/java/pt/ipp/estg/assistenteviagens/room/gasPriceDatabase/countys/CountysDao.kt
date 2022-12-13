package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.countys

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountysDao {
    @Query("select * from CountysDB")
    fun getCountys(): LiveData<List<CountysDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCountys(county: CountysDB)
}