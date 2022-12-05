package pt.ipp.estg.assistenteviagens.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.ipp.estg.assistenteviagens.room.entity.Car
import pt.ipp.estg.assistenteviagens.room.entity.UserWithCars

@Dao
interface CarsDao {

    @Transaction
    @Query("select * from User")
    fun getCars(): LiveData<List<UserWithCars>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(car: Car)

    @Delete
    suspend fun delete(car: Car)

}