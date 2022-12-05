package pt.ipp.estg.assistenteviagens.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.ipp.estg.assistenteviagens.room.entity.Cars

@Dao
interface CarsDao {

    @Query("select * from Cars")
    fun getCars(): LiveData<List<Cars>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(car: Cars)

    @Delete
    suspend fun delete(car: Cars)

}