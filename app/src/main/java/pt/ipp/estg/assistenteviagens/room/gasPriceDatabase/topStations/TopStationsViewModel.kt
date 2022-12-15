package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.topStations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.retrofit.RetroFitHelp
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI


class TopStationsViewModel(application: Application) : AndroidViewModel(application) {

    var repository: TopStationsRepository

    init {
        val db = TopStationsDatabase.getDatabase(application)
        val restAPI = RetroFitHelp.getInstance().create(RoutesAPI::class.java)
        repository = TopStationsRepository(db.getTopStationsDao(), restAPI)
    }

    fun getAllTopStations(id: Int): LiveData<List<TopStationsDB>> {
        viewModelScope.launch(Dispatchers.IO) {
            updateTopStationOnline(id);
        }
        return repository.getTopStations()
    }

    fun insertTopStations(topStations: TopStationsDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTopStations(topStations)
        }
    }

    fun updateTopStationOnline(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updateTopStationsOnline(id)
            if (response.isSuccessful) {
                val content = response.body()
                content?.resultado?.forEach {
                    insertTopStations(TopStationsDB(it.Id, it.Nome,it.Preco, it.Combustivel))
                }
            }
        }
    }
}