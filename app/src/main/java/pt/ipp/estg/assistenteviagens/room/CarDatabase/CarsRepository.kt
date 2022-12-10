package pt.ipp.estg.assistenteviagens.room.CarDatabase

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.room.CarDatabase.CarsDao
import pt.ipp.estg.assistenteviagens.room.CarDatabase.entity.Car

class CarsRepository(val carsDao: CarsDao) {

    /*
    fun getCars(): LiveData<List<UserWithCars>> {
        return carsDao.getCars()
    }
    */


    fun getCars(): LiveData<List<Car>> {
        return carsDao.getCars()
    }

    suspend fun insert(car: Car){
        carsDao.insert(car)
    }

    suspend fun delete(car: Car){
        carsDao.delete(car)
    }


}