package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.districts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.retrofit.RetroFitHelp
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI

class DistrictsViewModel(application: Application): AndroidViewModel(application) {

    val repository: DistrictsRepository


    init {
        val db = DistrictsDatabase.getDatabase(application)
        val restAPI = RetroFitHelp.getInstance().create(RoutesAPI::class.java)
        repository = DistrictsRepository(db.getDistrictDao(), restAPI)
    }

    fun getAllDistricts(): LiveData<List<DistrictsDB>> {
        viewModelScope.launch(Dispatchers.IO) {
            updateDistrictsOnline()
        }
        return repository.getDistrict()
    }

    fun insertDistrict(districts: DistrictsDB){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDistrict(districts)
        }
    }

    fun updateDistrictsOnline(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updateDistrictsOnline()
            if(response.isSuccessful){
                val content = response.body()
                content?.resultado?.forEach {
                    insertDistrict(DistrictsDB(it.Id,it.Descritivo))
                }
            }
        }
    }
}