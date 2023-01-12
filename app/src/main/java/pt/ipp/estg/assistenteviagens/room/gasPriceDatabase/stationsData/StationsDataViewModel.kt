package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.retrofit.RetroFitHelp
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.entitys.StationsDataDB

class StationsDataViewModel(application: Application) : AndroidViewModel(application) {
    val repository: StationsDataRepository

    init {
        val db = StationsDataDatabase.getDatabase(application)
        val restAPI = RetroFitHelp.getInstance().create(RoutesAPI::class.java)
        repository = StationsDataRepository(db.getStationsDataDao(), restAPI)
        deleteAllStationsData()
    }

    fun getStationsData(id: Int): LiveData<StationsDataDB> {
        viewModelScope.launch(Dispatchers.IO) {
            updateStationsDataOnline(id)
        }
        return repository.getStationsData()
    }

    fun getStationsDataByName(name:String): LiveData<StationsDataDB> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getStationsDataByName(name)
        }
        return repository.getStationsDataByName(name)
    }

    fun insertStationsData(info: StationsDataDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertStationsData(info)
        }
    }

    fun deleteAllStationsData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStationsData()
        }
    }

    fun updateStationsDataOnline(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updateStationsDataOnline(id)
            if (response.isSuccessful) {
                val content = response.body()
                val index = content?.resultado?.MeiosPagamento?.size
                content?.resultado?.let {
                    //for (x: Int in 0 until index!!) {
                    insertStationsData(
                        StationsDataDB(
                            it.Nome,
                            it.Marca,
                            it.Morada.Morada,
                            it.Morada.CodPostal,
                            it.Morada.Municipio,
                            it.Morada.Distrito,
                            it.Morada.Latitude,
                            it.Morada.Longitude,
                            it.HorarioPosto.DiasUteis,
                            it.HorarioPosto.Sabado,
                            it.HorarioPosto.Domingo,
                            it.HorarioPosto.Feriado,
                            it.MeiosPagamento[0].Descritivo,
                            it.Combustiveis[0].TipoCombustivel,
                            it.Combustiveis[0].Preco
                        )
                    )
                    //}
                }
            }
        }
    }
}