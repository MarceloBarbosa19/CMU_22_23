package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.entitys.Car

class CarViewModel (application: Application): AndroidViewModel(application) {
    var readAllData: LiveData<List<Car>>
    private var repository: CarRepository

    init {
        val carDao = CarDatabase.getInstance(application).carDao()
        repository = CarRepository(carDao)
        readAllData = repository.getallCars()
    }

    fun insertCar(car: Car){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCar(car)
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCar(car)
        }
    }
}