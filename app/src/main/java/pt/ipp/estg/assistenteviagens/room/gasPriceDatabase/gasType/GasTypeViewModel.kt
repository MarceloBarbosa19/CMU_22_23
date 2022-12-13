package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.retrofit.RetroFitHelp
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI

class GasTypeViewModel(application: Application) : AndroidViewModel(application) {
    val repository: GasTypeRepository

    init {
        val db = GasTypeDatabase.getDatabase(application)
        val restAPI = RetroFitHelp.getInstance().create(RoutesAPI::class.java)
        repository = GasTypeRepository(db.getGasTypeDao(), restAPI)
    }

    fun getAllGasTypes(): LiveData<List<GasTypeDB>> {
        viewModelScope.launch(Dispatchers.IO) {
            updateGasTypeOnline();
        }
        return repository.getGasType()
    }

    fun insertGasType(gasType: GasTypeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGas(gasType)
        }
    }

    fun updateGasTypeOnline() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updateGasTypeOnline()
            if (response.isSuccessful) {
                val content = response.body()
                content?.resultado?.forEach {
                    insertGasType( GasTypeDB( it.Id, it.Descritivo, it.UnidadeMedida,)
                    )
                }
            }
        }
    }
}