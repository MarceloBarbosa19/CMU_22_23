package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.entitys.Car

@Dao
interface CarDao {
    @Query("select * from Car")
    fun loadCars(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)
}