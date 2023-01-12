package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsSearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.retrofit.RetroFitHelp
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI

class StationsSearchViewModel(application: Application) : AndroidViewModel(application) {
    var repository: StationsSearchRepository

    init {
        val db = StationsSearchDatabase.getDatabase(application)
        val restAPI = RetroFitHelp.getInstance().create(RoutesAPI::class.java)
        repository = StationsSearchRepository(db.getStationsSearchDao(), restAPI)
        deleteAllPesquisa()
    }

    fun getSearchStations(
        idsTiposComb: Int,
        idMarca: Int,
        idDistrito: Int,
        idsMunicipios: Int
    ): LiveData<List<StationsSearchDB>> {
        viewModelScope.launch(Dispatchers.IO) {
            updateStationsSearchOnline(idsTiposComb, idMarca, idDistrito, idsMunicipios);
        }
        return repository.getStationsSearch()
    }

    fun getAllSearchStations(idsMunicipios: Int): LiveData<List<StationsSearchDB>> {
        viewModelScope.launch(Dispatchers.IO) {
            updateAllStationsSearchOnline(idsMunicipios);
        }
        return repository.getStationsSearch()
    }

    fun insertSearchStations(stationsSearchDB: StationsSearchDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertStationsSearch(stationsSearchDB)
        }
    }

    fun deleteAllPesquisa() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStationsSearch()
        }
    }

    fun updateStationsSearchOnline(
        idsTiposComb: Int,
        idMarca: Int,
        idDistrito: Int,
        idsMunicipios: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                repository.updateStationsSearchOnline(idsTiposComb, idMarca, idDistrito, idsMunicipios)
            if (response.isSuccessful) {
                val content = response.body()
                content?.resultado?.forEach {
                    insertSearchStations(StationsSearchDB(it.Id, it.Nome))
                }
            }
        }
    }

    fun updateAllStationsSearchOnline(idsMunicipios: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                repository.updateStationsSearchOnline(idsMunicipios)
            if (response.isSuccessful) {
                val content = response.body()
                content?.resultado?.forEach {
                    insertSearchStations(StationsSearchDB(it.Id, it.Nome))
                }
            }
        }
    }
}