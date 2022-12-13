package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.countys

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.retrofit.RetroFitHelp
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI


class CountysViewModel(application: Application): AndroidViewModel(application) {
    val repository: CountysRepository

    init {
        val db = CountysDatabase.getDatabase(application)
        val restAPI = RetroFitHelp.getInstance().create(RoutesAPI::class.java)
        repository = CountysRepository(db.getCountysDao(), restAPI)
    }

    fun getAllCountys(): LiveData<List<CountysDB>> {
        viewModelScope.launch(Dispatchers.IO) {
            updateDistrictsOnline()
        }
        return repository.getCountys()
    }

    fun insertCountys(county: CountysDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCountys(county)
        }
    }

    fun updateDistrictsOnline() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updateCountysOnline()
            if (response.isSuccessful) {
                val content = response.body()
                content?.resultado?.forEach {
                    insertCountys(CountysDB(it.Id, it.Descritivo, it.IdDistrito))
                }
            }
        }
    }
}