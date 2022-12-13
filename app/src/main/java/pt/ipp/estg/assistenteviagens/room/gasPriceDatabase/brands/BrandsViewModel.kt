package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.brands

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.retrofit.RetroFitHelp
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI

class BrandsViewModel(application: Application) : AndroidViewModel(application) {

    val repository: BrandsRepository

    init {
        val db = BrandsDatabase.getDatabase(application)
        val restAPI = RetroFitHelp.getInstance().create(RoutesAPI::class.java)
        repository = BrandsRepository(db.getBrandsDao(), restAPI)
    }

    fun getAllBrands(): LiveData<List<BrandsDB>> {
        viewModelScope.launch(Dispatchers.IO) {
            updateStationsOnline()
        }
        return repository.getBrands()
    }

    fun insertBrands(brand: BrandsDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBrands(brand)
        }
    }


    private fun updateStationsOnline() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updateBrandsOnline()
            if(response.isSuccessful){
                val content = response.body()
                content?.resultado?.forEach {
                    insertBrands(BrandsDB(it.Id,it.Descritivo))
                }
            }
        }
    }
}