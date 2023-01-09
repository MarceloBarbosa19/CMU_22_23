package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.entitys.Car

class CarRepository (private val carDao: CarDao) {
    fun getallCars(): LiveData<List<Car>> {
        return carDao.loadCars()
    }

    suspend fun insertCar(car: Car) {
        carDao.insertCar(car)
    }

    suspend fun deleteCar(car: Car) {
        carDao.deleteCar(car)
    }
}