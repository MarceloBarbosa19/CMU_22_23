package pt.ipp.estg.assistenteviagens.room.CarDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.ipp.estg.assistenteviagens.room.CarDatabase.entity.Car

@Dao
interface CarsDao {

    /*
    @Transaction
    @Query("select * from User")
    fun getCars(): LiveData<List<UserWithCars>>
    */

    @Query("select * from Car")
    fun getCars(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(car: Car)

    @Delete
    suspend fun delete(car: Car)

}