package pt.ipp.estg.assistenteviagens.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.room.dataBase.CarsDataBase
import pt.ipp.estg.assistenteviagens.room.entity.Car
import pt.ipp.estg.assistenteviagens.room.entity.UserWithCars
import pt.ipp.estg.assistenteviagens.room.repository.CarsRepository

class CarViewModel(application: Application) : AndroidViewModel(application) {

    val repository: CarsRepository
    val allCars: LiveData<List<UserWithCars>>

    init {
        val db = CarsDataBase.getDatabase(application)
        repository = CarsRepository(db.getCarsDao())
        allCars = repository.getCars()
    }

    fun insertCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(car)
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(car)
        }
    }

}