package pt.ipp.estg.assistenteviagens.room.repository

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.room.dao.CarsDao
import pt.ipp.estg.assistenteviagens.room.entity.Cars

class CarsRepository(val carsDao: CarsDao) {

    fun getCars(): LiveData<List<Cars>> {
        return carsDao.getCars()
    }

    suspend fun insert(car:Cars){
        carsDao.insert(car)
    }

    suspend fun delete(car:Cars){
        carsDao.delete(car)
    }


}